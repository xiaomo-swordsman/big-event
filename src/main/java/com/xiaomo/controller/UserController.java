package com.xiaomo.controller;

import com.xiaomo.pojo.Result;
import com.xiaomo.pojo.User;
import com.xiaomo.service.EmailService;
import com.xiaomo.service.UserService;
import com.xiaomo.util.JwtUtil;
import com.xiaomo.util.Md5Util;
import com.xiaomo.util.RedisUtil;
import com.xiaomo.util.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/*
    用户模块
        1、注册
        2、登录
        3、获取用户详细信息
        4、更新用户基本信息
        5、更新用户头像
        6、更新用户密码

 */

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // localhost:9009/dev/user/testUserController
    @RequestMapping("/testUserController")
    public String testUserController() throws Exception {
//        JwtUtil.genTokenByRSA();

        // 测试邮件发送
        // emailService.sendEmail("xiaomo_swordsman@163.com","test","this is a test message");

        return "测试 访问userController";
    }

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$",message = "username参数必须5~16位") String username, @Pattern(regexp = "^\\S{5,16}$",message = "password参数必须5~16位")String password){
        User user = userService.findUserByName(username);
        // 判断用户是否存在
        if(user == null){
            // 注册用户
            userService.addUser(username,password);
            return Result.success();
        }else{

            return Result.error("用户已存在，注册失败");
        }

    }

    @PostMapping("/login")
    // localhost:9009/dev/user/login?username=teng.yang&password=123456
    public Result login(@Pattern(regexp = "^\\S{5,16}$",message = "username参数必须5~16位") String username,
                        @Pattern(regexp = "^\\S{5,16}$",message = "password参数必须5~16位")String password){
        User user = userService.findUserByName(username);
        System.out.println("username = " + username);
        if(user == null){
            // 用户不存在
            return Result.error("用户名错误");
        }
        if(Md5Util.getMD5String(password).equals(user.getPassword())){
            // 登录成功后 返回token
            try{
                Map<String,Object> claim = new HashMap<String,Object>();
                claim.put("id",user.getId());
                claim.put("userName",user.getUsername());
                ThreadLocalUtil.set(claim);
                String token = JwtUtil.genRSAToken(claim);
                // 登陆成功后，将token 也存入到redis中
                stringRedisTemplate.opsForValue().set(token,token,1, TimeUnit.HOURS);

                return Result.success(token);
            }catch (Exception e){
                e.printStackTrace();
                return Result.error("生成token报错");
            }
        }
        return Result.error("密码错误");
    }

    @RequestMapping("/userInfo")
    // localhost:9009/dev/user/userInfo
    public Result userInfo(@RequestHeader(name = "Authorization") String token){
        // 根据用户名获取用户
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = String.valueOf(map.get("userName"));
        User user = userService.findUserByName(username);
        return Result.success(user);
    }

    // 更新用户信息
    // content-type:application/json
    // method:put
    @PutMapping("/update")
    // localhost:9009/dev/user/update
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    // 更新用户头像
    // content-type:application/x-www-form-urlencoded
    // method:patch
    // @URL 参数校验，校验格式需要为url格式的
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){

        // 获取到当前用户
        Map<String, Object> map = ThreadLocalUtil.get();
        int id = (Integer)map.get("id");
        userService.updateAvatar(avatarUrl,id);
        return Result.success();
    }

    @PatchMapping("/updatePassword")
    public Result updatePassword(@RequestBody Map<String,Object> paramsMap, @RequestHeader(name="Authorization") String token){
        // 校验传来的密码、新密码、确认密码是否为空
        String oldPassword = String.valueOf(paramsMap.get("old_password"));
        String newPassword = String.valueOf(paramsMap.get("new_password"));
        String rePassword = String.valueOf(paramsMap.get("re_password"));

        //
        if(!StringUtils.hasLength(oldPassword) || !StringUtils.hasLength(newPassword) || !StringUtils.hasLength(rePassword)){
            return Result.error("缺少必要参数");
        }

        // 校验旧密码是否正确
        Map<String,Object> claim = (Map<String,Object>)ThreadLocalUtil.get();
        String username = String.valueOf(claim.get("userName"));
        User user = userService.findUserByName(username);
        if(!Md5Util.getMD5String(oldPassword).equals(user.getPassword())){
            return Result.error("原密码错误");
        }

        // 校验用户确认密码是否一致
        if(!newPassword.equals(rePassword)){
            return Result.error("前后密码不一致");
        }

        // 更新密码
        userService.updatePassword(newPassword,user.getId());

        // 修改密码后，应该需要删除token，登陆时候重新获取最新token
        stringRedisTemplate.delete(token);

        // 响应到前端
        return Result.success();
    }
}
