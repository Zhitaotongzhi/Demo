package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;


import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    private static final String SECRET = "jwt_secret";

    public static String sign(String username,Map<String,Object> info){
        try{
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    //将username保存到token里
                    //.withAudience(username)
                    //.withClaim("info",info)
                    //.withExpiresAt(date)
                    .sign(algorithm);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //根据token获取username
    public static String getUsername(String token){
        try{
            String username = JWT.decode(token).getAudience().get(0);
            return username;
        }catch (JWTDecodeException e){
            return null;
        }
    }
    //根据token获取自定义数据
    public static Map<String,Object> getInfo(String token){
        try{
            return JWT.decode(token).getClaim("info").asMap();
        }catch(JWTDecodeException e){
            return null;
        }
    }
    //校验token
    public static boolean checkSign(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            verifier.verify(token);
            return true;
        }catch(JWTVerificationException e){
            throw new RuntimeException("token无效，请重新获取");
        }
    }
}
