package com.example.demo.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.example.demo.dao.WorkerDao;
import com.example.demo.domain.*;
import com.example.demo.dao.AdminDao;
import com.example.demo.service.AdminService;
import com.example.demo.service.WorkerService;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.Result;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.RegExUtil;

import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.demo.domain.Pager;
import com.example.demo.utils.ResultCode;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AdminController {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private AdminService adminService;

    @Autowired
    private WorkerDao workerDao;

    @Autowired
    private WorkerService workerService;

    //管理员登录
    /*@PostMapping ("/user/login")
    public String adminLogin(HttpServletRequest request, HttpServletResponse response){
        //前端获取数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
       // 接收前端数据
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);

        boolean login = adminService.login(admin);
        if(login == true){
            return "管理员";
        }else{
            return "登陆失败";
        }
    }*/

    //工作人员登录返回result类
    @PostMapping("/user/login")
    public Result adminLogin(@RequestBody Worker worker){
        String username = worker.getUsername();
        String password = worker.getPassword();
        Worker new_worker = workerDao.selectWorkerByUsername(username);
        String login = workerService.login(worker);
        System.out.println(login);
        if(login.equals("该用户已登录")){
            return Result.failure("3", "用户已登录");
        }else if(login.equals("该用户不存在")){
            return Result.failure("2", "用户不存在");
        }else if(login.equals("登录出错")){
            return Result.failure("2", "登录出错");
        }else if(login.equals("登录成功")){
            workerDao.updateIsOnline(username);
            Map<String, Object> user = new HashMap<>();
            String token = JwtUtil.sign(worker.getUsername(), user);
            user.put("token", token);
            user.put("user", new_worker);
            return Result.success(user);
        }else{
            return Result.failure("2", "系统错误");
        }
    }

    //修改密码
    @PostMapping("/user/changePassword")
    public Result changePassword(@RequestBody Worker worker){
        workerDao.updatePassword(worker.getPassword(), worker.getUsername());
        return Result.success();
    }
    //注册新咨询师
    /*@PostMapping("/user/addCon")
    public Result addConsultant(@RequestBody Consultant consultant){

        /*String name = consultant.getName();
        String username = consultant.getUsername();
        String bind_username = consultant.getBind_username();
        String password = consultant.getPassword();
        String gender = consultant.getGender();
        String pno = consultant.getPno();
        String email = consultant.getEmail();
        String authority = consultant.getAuthority();

        Consultant new_consultant = new Consultant();


        new_consultant.setName(name);
        new_consultant.setUsername(username);
        new_consultant.setBind_username(bind_username);
        new_consultant.setPassword(password);
        new_consultant.setGender(gender);
        new_consultant.setPno(pno);
        new_consultant.setEmail(email);
        new_consultant.setAuthority(authority);*/
        //boolean flag = adminService.regConsultant(consultant);
       // if(flag){
         //   return Result.success();
       // }else{
       //     return Result.failure("20005","用户已存在");
       // }
   // }

    //管理员注册新督导
    /*@GetMapping("/RegSup")
    public String regSup(HttpServletRequest request, HttpServletResponse response){
        //获取前端数据
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String pno = request.getParameter("pno");
        String email = request.getParameter("email");
        String authority = request.getParameter("authority");
        String title = request.getParameter("title");
        String workunit = request.getParameter("workunit");
        //接收前端数据
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
            return "添加成功";
        }else{
            return "添加失败";
        }
    }*/
    //注册新的督导
    /*@PostMapping("/user/addSup")
    public Result addSupervisor(@RequestBody Supervisor supervisor){

        boolean flag = adminService.regSupervisor(supervisor);
        if(flag){
            return Result.success();
        }else{
            return Result.failure("20005","用户已存在");
        }
    }*/

    //更新访客账号状态
    @PatchMapping ("/visitors/update/{pno}")
    public Result updateVisitor(@PathVariable String pno){
        Visitor visitor = adminDao.selectByPno(pno);
        String status = visitor.getStatus();
        System.out.println(status);
        if(status == "normal"){
           adminDao.bannedByPno(pno);
            return Result.success();
        }else if(status == "banned"){
            adminDao.enableByPno(pno);
            return Result.success();
        }else{
            return Result.failure("0","操作失败");
        }
    }


    //工作人员离线
    @PatchMapping("/user/exit/{username}")
    public Result logout(@PathVariable String username){
        workerDao.updateIsOffline(username);
        return Result.success();
    }

    //为咨询师绑定督导
    @PostMapping("/user/bind")
    public Result bind(@RequestBody Bind counselor, @RequestBody Bind supervisor){
        String counselorName = counselor.getName();
        String counselorUsername = counselor.getUsername();
        String supervisorName = supervisor.getName();
        String supervisorUsername = supervisor.getUsername();
        workerDao.insertBind(counselorName, counselorUsername, supervisorName, supervisorUsername);
        return Result.success();
    }

}
