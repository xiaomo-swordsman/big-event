package com.xiaomo.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 响应json数据工具类
 * @author Gongwangwang
 * @email  gww.vip@qq.com
 * @date   2020-04-20
 */
public class RespUtil {
    private ObjectMapper mapper = new ObjectMapper();
    /**
     * 响应json数据
     * @param jsonStr json字符串
     * @param response 响应对象
     */
    public static void output(String jsonStr, HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=UTF-8;");
        PrintWriter out = response.getWriter();
        try {
            out.println(jsonStr);
        } finally {
            out.flush();
            out.close();
        }
    }
    /**
     * 响应json数据
     * @param reusltMap json字符串
     * @param response 响应对象
     */
    public static void output(Map<String,Object> reusltMap, HttpServletResponse response) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        mapper.writeValue(response.getWriter(), reusltMap);
    }
}
