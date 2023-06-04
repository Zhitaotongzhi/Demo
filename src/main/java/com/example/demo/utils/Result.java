package com.example.demo.utils;

import java.io.Serializable;

public class Result implements Serializable{
    private static final long SerialVersionUID = 1L;

    private String code;
    private String msg;
    private Object data;

    private Result(){}

    private Result(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    private void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }
    //操作失败
    public static Result failure(String code, String msg) {
        Result result = new Result(code, msg);
        return result;
    }
    //操作成功，无返回数据
    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }
    //操作成功，有返回数据
    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }
    //操作失败，无返回数据
    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }
    //操作失败，有返回数据
    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public static long getSerialVersionUID() {
        return SerialVersionUID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
