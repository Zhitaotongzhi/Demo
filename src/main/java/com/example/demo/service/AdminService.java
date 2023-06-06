package com.example.demo.service;

import com.example.demo.dao.AdminDao;
import com.example.demo.domain.Admin;
import com.example.demo.domain.Consultant;
import com.example.demo.domain.Supervisor;
import com.example.demo.domain.Visitor;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressWarnings("all")
@Service

public class AdminService {
    @Autowired
    AdminDao adminDao;
    private static String regEx1 = "^[a-zA-Z_]+$";
    private static String regEx2 = "^.{6,}$";

    //管理员登录
    public boolean login(Admin admin) {
        String loginUsername = admin.getUsername();
        String loginPassword = admin.getPassword();
        Pattern Password_Pattern = Pattern.compile(regEx1);
        Matcher matcher = Password_Pattern.matcher(loginUsername);

        Admin loginAdmin = adminDao.selectAdByUsername(loginUsername);
        System.out.println("进行验证");
        System.out.println(loginUsername);

        if (loginAdmin == null) {
                return false;
        } else if (loginAdmin.getPassword().equals(loginPassword)) {
                return true;
        } else {
                return false;
        }

        /*Admin loginAdmin = adminDao.selectAdByUsername(loginUsername);
        System.out.println("进行验证");
        System.out.println(loginUsername);
        if (loginAdmin == null) {
            return "10002";
        } else if (loginAdmin.getPassword().equals(loginPassword)) {
            return "1";
        } else {
            return "2";
        }*/
    }

    //注册新的咨询师
    public boolean regConsultant(Consultant consultant) {
        int i = adminDao.insertConsultant(consultant);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    //完整返回注册新的咨询师
    public String regCounselor(Consultant consultant){
        String username = consultant.getUsername();
        Consultant new_consultant = adminDao.selectConByUsername(username);
        if(new_consultant == null){
            int i = adminDao.insertConsultant(consultant);
            if(i > 0){
                return "成功注册";
            }else{
                return "注册失败";
            }
        }else{
            return "已存在该用户";
        }
    }

    /*//注册新的督导
    public boolean regSupervisor(Supervisor supervisor) {
        int i = adminDao.insertSupervisor(supervisor);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }*/

    //完整返回注册新的督导
    public String regSupervisor(Supervisor supervisor){
        String username = supervisor.getUsername();
        Supervisor new_supervisor = adminDao.selectSupByUsername(username);
        if(new_supervisor == null){
            int i = adminDao.insertSupervisor(supervisor);
            if(i > 0){
                return "注册成功";
            }else{
                return "注册失败";
            }
        }else{
            return "用户已存在";
        }
    }

}
