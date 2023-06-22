package com.example.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtil {
    private static String regEx_Passwprd = "^.{6,}$";
    private static String regEx_Username = "^[a-zA-Z_]+$";
    private static String regEx_Name = "^[\u4e00-\u9fa5a-zA-Z]{2,32}$";
    private static String regEx_Pno = "^1[3-9]\\d{9}$";

    public static String detectPassword(String password){
        Pattern pattern_Password = Pattern.compile(regEx_Passwprd);
        Matcher matcher = pattern_Password.matcher(password);
        if(!matcher.matches()){
            return "密码格式错误";
        }else{
            return "密码格式正确";
        }
    }

    public static String detectUsername(String username){
        Pattern pattern_Username = Pattern.compile(regEx_Username);
        Matcher matcher = pattern_Username.matcher(username);
        if(!matcher.matches()){
            return "用户名格式错误";
        }else{
            return "用户名格式正确";
        }
    }

    public static String detectName(String name){
        Pattern pattern_Name = Pattern.compile(regEx_Name);
        Matcher matcher = pattern_Name.matcher(name);
        if(!matcher.matches()){
            return "姓名格式错误";
        }else{
            return "姓名格式正确";
        }
    }

    public static String detectPno(String pno){
        Pattern pattern_Pno = Pattern.compile(regEx_Pno);
        Matcher matcher = pattern_Pno.matcher(pno);
        if(!matcher.matches()){
            return "电话号码格式错误";
        }else{
            return "电话号码格式正确";
        }
    }
}
