package com.example.demo.controller;

import com.example.demo.dao.VisitorDao;
import com.example.demo.domain.Record;
import com.example.demo.domain.Visitor;
import com.example.demo.service.VisitorService;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.demo.dao.AdminDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings({"all"})
@RestController
@CrossOrigin
@RequestMapping("/visitors")
public class VisitorController {
    @Autowired
    VisitorService visitorService;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private VisitorDao visitorDao;

    //登录
    @PostMapping("/login")
    public Result visitorLogin(@RequestBody Visitor visitor){
        String name = visitor.getName();
        String pno = visitor.getPno();
        Visitor new_visitor = visitorDao.selectVisByPno(pno);
        boolean login = visitorService.login(visitor);
        if(login == true){
            Map<String, Object> user = new HashMap<>();
            String token = JwtUtil.sign(name,user);
            user.put("token", token);
            user.put("visitor", new_visitor);
            return Result.success(user);
        }else if(login == false){
            return Result.failure("20001","账号不存在或密码错误");
        }else{
            return Result.failure("2", "参数无效");
        }
    }

    //注册
    @PostMapping("/insert")
    public Result visitorInsert(@RequestBody Visitor visitor){
        String flag = visitorService.reg(visitor);
        if(flag.equals("注册成功")){
            return Result.success();
        }else if(flag.equals("用户已存在")){
            return Result.failure("20005", "用户已存在");
        }else{
            return Result.failure("2", "操作失败");
        }
    }

    //更新
    @PatchMapping("/update")
    public Result visitorUpdate(@RequestBody Visitor visitor){
        String flag = visitorService.updateVisitor(visitor);
        if(flag.equals("修改成功")){
            return Result.success();
        }else if(flag.equals("修改失败")){
            return Result.failure("2", "操作失败");
        }else if(flag.equals("用户不存在")){
            return Result.failure("20004", "用户不存在");
        }else{
            return Result.failure("2", "操作失败");
        }
    }

    //访客列表
    @GetMapping("/list")
    public Result visitorList(@RequestParam String pageNum,@RequestParam String pageSize,@RequestParam String search){
        int i = Integer.parseInt(pageNum);
        int j = Integer.parseInt(pageSize);
        if(search.isEmpty()){
            List<Visitor> list = adminDao.findAllVisitorByPage( (i - 1) * j, j);
            long total = adminDao.findVisitorCount();
            Map<String, Object> visitor = new HashMap<>();
            visitor.put("total",total);
            visitor.put("visitor",list);
            return Result.success(visitor);
        }else{
            List<Visitor> list = visitorDao.findallVisitor((i - 1) * j, j, search);
            long total = visitorDao.findVisitorCount();
            Map<String, Object> visitor = new HashMap<>();
            visitor.put("total",total);
            visitor.put("visitor",list);
            return Result.success(visitor);
        }
    }
    //禁用启用
    @PatchMapping("/banned/{pno}")
    public Result bannedVisitor(@PathVariable String pno){
        Visitor visitor = adminDao.selectByPno(pno);
        adminDao.bannedByPno(pno);
        return Result.success();
    }

    @PatchMapping("/enable/{pno}")
    public Result enableVisitor(@PathVariable String pno){
        Visitor visitor = adminDao.selectByPno(pno);
        adminDao.enableByPno(pno);
        return Result.success();
    }

    //修改密码
    @PostMapping("/updatePassword/{pno}")
    public Result updatePassword(@RequestParam String password, @PathVariable String pno){
        visitorDao.updatePassword(password, pno);
        return Result.success();
    }

    //获得访客名字
    @PostMapping("/name")
    public Result visitorName(@RequestParam String pno){
        String name = visitorDao.findName(pno);
        System.out.println(name+"============");
//        Map<String, Object> visitorName = new HashMap<>();
//        visitorName.put("name",name);
        return Result.success(name);
    }


}
