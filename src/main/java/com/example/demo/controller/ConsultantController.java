package com.example.demo.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.example.demo.dao.AdminDao;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.domain.Consultant;
import com.example.demo.dao.ConsultantDao;
import com.example.demo.service.ConsultantService;
import com.example.demo.service.AdminService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/counselors")
public class ConsultantController {
    @Autowired
    private ConsultantDao consultantDao;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private AdminService adminService;

    //查询咨询师列表
    @GetMapping("/list")
    public Result conselorList(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam String search){
        int i = Integer.parseInt(pageNum);
        int j = Integer.parseInt(pageSize);
        if(search.isEmpty()){
            List<Consultant> list = consultantDao.findAllConsultantByAuthorityNoName((i - 1) * j, j);
            long total = consultantDao.findAllConsultantCountNoName();
            Map<String, Object> counselor = new HashMap<>();
            counselor.put("total",total);
            counselor.put("Counselors",list);
            return Result.success(counselor);
        }else{
            List<Consultant> list = consultantDao.findAllConsultantByAuthority((i - 1) * j, j, search);
            long total = consultantDao.findAllCountCount();
            Map<String, Object> counselor = new HashMap<>();
            counselor.put("total",total);
            counselor.put("Counselors",list);
            return Result.success(counselor);
        }
    }

    /*//新注册咨询师
    @PostMapping("/insert")
    public Result addCounselor(@RequestBody Consultant consultant){
        boolean flag = adminService.regConsultant(consultant);
        if(flag){
            return Result.success();
        }else{
            return Result.failure("20005","用户已存在");
        }
    }*/

    //完整返回信息的注册咨询师
    @PostMapping("/insert")
    public Result addConsultant(@RequestBody Consultant consultant){
        String flag = adminService.regCounselor(consultant);
        if(flag.equals("成功注册")){
            return Result.success();
        }else if(flag.equals("已存在该用户")){
            return Result.failure("20005","用户已存在");
        }else{
            return Result.failure("2","操作失败");
        }
    }

    //禁用咨询师账号
    @PatchMapping("/banned/{username}")
    public Result bannedCounselor(@PathVariable String username){
        Consultant consultant = consultantDao.selectCounselor(username);
        consultantDao.bannedCounselor(username);
        return Result.success();
    }

    //启用咨询师账号
    @PatchMapping("/enable/{username}")
    public Result enableCounselor(@PathVariable String username){
        Consultant consultant = consultantDao.selectCounselor(username);
        consultantDao.enableCounselor(username);
        return Result.success();
    }

    //更新咨询师账号信息
    @PatchMapping("/update")
    public Result updateCounselor(@RequestBody Consultant consultant){
        consultantDao.updateCounselorByUsername(consultant);
        return Result.success();
    }

    //查看在线咨询师列表
    @GetMapping("/online/list")
    public Result findAllConIsOnline(){
        List<Consultant> list = consultantDao.findAllConIsOnline();
        long total = consultantDao.findAllConIsOnlineCount();
        Map<String, Object> counselor = new HashMap<>();
        counselor.put("total", total);
        counselor.put("counselors", list);
        return Result.success(counselor);
    }
}
