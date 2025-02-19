package com.xiaomo.interceptor;

import com.xiaomo.pojo.Result;
import com.xiaomo.util.JwtUtil;
import com.xiaomo.util.RedisUtil;
import com.xiaomo.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    public RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        // 解决跨域，跨域时候，浏览器会先发送options请求，需要后端正确处理，否则会造成Authorization 头部信息丢失
        // 在拦截器中忽略 OPTIONS 请求
        if(HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            return true;
        }

        // 获取token
        String token = request.getHeader("Authorization");

        // 验证token
        try{
            // 判断token 是否还存在redis中，如果不存在，则抛出异常
            String redisToken = redisUtil.get(token);
            if(!StringUtils.hasLength(redisToken)){
                throw new RuntimeException();
            }

            Map<String, Object> claim = JwtUtil.parseRSAToken(token);
            ThreadLocalUtil.set(claim);
            System.out.println("验证通过，放行");
            return true;// 验证通过，放行
        }catch (Exception e){
            // http响应401
            e.printStackTrace();
            response.setStatus(401);
            System.out.println("验证失败，不放行");
            return false; // 验证失败，不放行
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        // 清空ThreadLocal中的数据
        ThreadLocalUtil.remove();
    }
}
