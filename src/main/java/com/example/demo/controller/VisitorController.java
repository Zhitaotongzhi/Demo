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

    /*@RequestMapping("/tologin")
    public String login(){
        return "Login";
    }
    @RequestMapping("/result")
    public String login(HttpServletRequest request, HttpServletResponse response){
        //前端获取数据
        String name = request.getParameter("name");
        String pno = request.getParameter("pno");

        //接受前端数据
        Visitor visitor = new Visitor();
        visitor.setName(name);
        visitor.setPno(pno);

        //调用service层方法
        boolean login = visitorService.login(visitor);
        if(login == true){
            return "Success";
        }else{
            return "Login";
        }
    }*/
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

    /*@GetMapping("/reg")
    public String reg(){
        return "Reg";
    }
    @GetMapping("/end")
    public String end(HttpServletRequest request, HttpServletResponse response){
        //获取前端数据
        String name = request.getParameter("name");
        String phonenumber = request.getParameter("phonenumber");
        String contact_name = request.getParameter("contect_name");
        String contact_pno = request.getParameter("contact_pno");
        //接收数据
        Visitor visitor = new Visitor();

        visitor.setName(name);
        visitor.setPno(phonenumber);
        visitor.setContact_name(contact_name);
        visitor.setContact_pno(contact_pno);

        System.out.println("插入数据！");

        boolean flag = visitorService.reg(visitor);
        if(flag){
            return "END1";
        }else{
            return "END2";
        }
    }*/

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

}
