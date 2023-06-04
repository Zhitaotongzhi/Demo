package com.example.demo.interceptor;

import com.example.demo.utils.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
        //如果不映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        //从http请求头中取出token
        String token = request.getHeader("token");
        System.out.println("检测是否收到token:" + token);

        if(token == null){
            throw new RuntimeException("无token，请重新登录");
        }

        //验证token
        JwtUtil.checkSign(token);
        //通过验证后，取出JWT中所存放的数据
        String username = JwtUtil.getUsername(token);
        System.out.println("username:" + username);
        Map<String,Object> info = JwtUtil.getInfo(token);
        info.forEach((k, v) -> System.out.println(k + ":" + v));
        return true;
    }
}
