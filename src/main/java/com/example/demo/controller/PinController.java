package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class PinController {
    private String apiUrl = "https://sms_developer.zhenzikj.com";
    private String appId = "113156";
    private String appSecret = "7bd748fb-7a89-4d80-a580-49ea0a22f24a";

    @GetMapping("/sendCode/{pno}")
    public String sendCode(@PathVariable String pno){
        try{
            JSONObject json;
            String code = String.valueOf(ThreadLocalRandom.current().nextInt(100000,1000000));
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl,appId,appSecret);
            Map<String, Object> params = new HashMap<>();
            params.put("number", pno);
            params.put("templateId",11675);
            String[] templateParams = new String[2];
            templateParams[0] = code;
            templateParams[1] = "2";
            params.put("templateParams",templateParams);
            String result = client.send(params);
            System.out.println(result);
            json = JSONObject.parseObject(result);
            if(json.getIntValue("code")!= 0){
                return null;
            }
            return code;

        }catch(Exception e){
            e.printStackTrace();
            return "code not found";
        }
    }
    @RequestMapping("/goSendCode")
    public String goSendCode(){
        return "/sendCode";
    }
}
