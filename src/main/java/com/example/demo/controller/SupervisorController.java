package com.example.demo.controller;

import com.example.demo.*;
import com.example.demo.dao.AdminDao;
import com.example.demo.dao.ConsultantDao;
import com.example.demo.dao.SupervisorDao;
import com.example.demo.domain.Consultant;
import com.example.demo.domain.Supervisor;
import com.example.demo.service.AdminService;
import com.example.demo.service.SupervisorService;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("all")
@RestController
@CrossOrigin
@RequestMapping("/supervisors")
public class SupervisorController {
    @Autowired
    private SupervisorDao supervisorDao;

    @Autowired
    private SupervisorService supervisorService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ConsultantDao consultantDao;

    //查看督导列表
    @GetMapping("/list")
    public Result supervisorList(@RequestParam String pageNum,@RequestParam String pageSize,@RequestParam String search){
        int i = Integer.parseInt(pageNum);
        int j = Integer.parseInt(pageSize);
        if(search.isEmpty()){
            List<Supervisor> list = supervisorDao.findAllSupervisor((i - 1) * j, j);
            long total = supervisorDao.findAllSupervisorCountNoName();
            Map<String, Object> supervisor = new HashMap<>();
            supervisor.put("total",total);
            supervisor.put("supervisors",list);
            return Result.success(supervisor);
        }else{
            List<Supervisor> list = supervisorDao.findAllSupervisorByAuthority((i - 1) * j, j, search);
            long total = supervisorDao.findAllSupervisorCount();
            Map<String, Object> supervisor = new HashMap<>();
            supervisor.put("total",total);
            supervisor.put("supervisors",list);
            return Result.success(supervisor);
        }
    }

    /*@PostMapping("/insert")
    public Result addSupervisor(HttpServletRequest request){
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String pno = request.getParameter("pno");
        String email = request.getParameter("email");
        String authority = request.getParameter("authority");
        String title = request.getParameter("title");
        String workunit = request.getParameter("workunit");

        Supervisor supervisor = new Supervisor();

        supervisor.setName(name);
        supervisor.setUsername(username);
        supervisor.setPassword(password);
        supervisor.setGender(gender);
        supervisor.setPno(pno);
        supervisor.setEmail(email);
        supervisor.setAuthority(authority);
        supervisor.setTitle(title);
        supervisor.setWorkunit(workunit);

        boolean flag = adminService.regSupervisor(supervisor);
        if(flag){
            return Result.success();
        }else{
            return Result.failure("20005","用户已存在");
        }
    }*/
    /*//注册新的督导
    @PostMapping("/insert")
    public Result addSupervisor(@RequestBody Supervisor supervisor){
        boolean flag = adminService.regSupervisor(supervisor);
        if(flag){
            return Result.success();
        }else{
            return Result.failure("20005","用户已存在");
        }
    }*/

    //完整返回信息的督导注册
    @PostMapping("/insert")
    public Result addSupervisor(@RequestBody Supervisor supervisor){
        String flag = adminService.regSupervisor(supervisor);
        if(flag.equals("注册成功")){
            return Result.success();
        }else if(flag.equals("用户已存在")){
            return Result.failure("20005", "用户已存在");
        }else{
            return Result.failure("0", "注册失败");
        }
    }
    //禁用督导账号
    @PatchMapping("/banned/{username}")
    public Result bannedSupervisor(@PathVariable String username){
        Supervisor supervisor = supervisorDao.selectSupervisorByUsername(username);
        supervisorDao.bannedSupervisor(username);
        return Result.success();
    }
    //启用督导账号
    @PatchMapping("/enable/{username}")
    public Result enableSupervisor(@PathVariable String username){
        Supervisor supervisor = supervisorDao.selectSupervisorByUsername(username);
        supervisorDao.enableSupervisor(username);
        return Result.success();
    }
    //更新督导信息
    @PatchMapping("/update")
    public Result updateSupervisor(@RequestBody Supervisor supervisor){
        supervisorDao.updeteSupervisorByUsername(supervisor);
        return Result.success();
    }

    //督导查看在线咨询师列表
    @GetMapping("/onlineList/{username}")
    public Result onlineList(@PathVariable String username, @RequestParam String pageNum, @RequestParam String pageSize){
        int i = Integer.parseInt(pageNum);
        int j = Integer.parseInt(pageSize);
        List<Consultant> counselors = consultantDao.findAllBindIsOnline((i - 1) * j, j, username);
        long total = counselors.size();
        Map<String, Object> counselor = new HashMap<>();
        counselor.put("total", total);
        counselor.put("counselors", counselors);
        return Result.success(counselor);
    }
}
