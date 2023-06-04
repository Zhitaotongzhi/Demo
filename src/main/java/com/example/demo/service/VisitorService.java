package com.example.demo.service;

import com.example.demo.dao.VisitorDao;
import com.example.demo.domain.Record;
import com.example.demo.domain.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@SuppressWarnings({"all"})
@Service

public class VisitorService {
    @Autowired
    VisitorDao visitorDao;

    //访客登录
    public boolean login(Visitor visitor){
        String loginPno = visitor.getPno();
        String loginPassword = visitor.getPassword();

        Visitor loginVisitor = visitorDao.selectVisByPno(loginPno);
        System.out.println("进行验证");
        System.out.println(loginPno);

        if(loginVisitor == null){
            return false;
        } else if (loginVisitor.getPassword().equals(loginPassword)) {
            return true;
        }else {
            return false;
        }
    }

    //访客注册
    /*public boolean reg(Visitor visitor){
        int i = visitorDao.insertVisitor(visitor);
        if(i > 0){
            return true;
        }else{
            return false;
        }
    }*/
    public String reg(Visitor visitor){
        String pno = visitor.getPno();
        Visitor new_visitor = visitorDao.selectVisByPno(pno);
        if(new_visitor == null){
            int i = visitorDao.insertVisitor(visitor);
            if(i > 0){
                return "注册成功";
            }else{
                return "注册失败";
            }
        }else{
            return "用户已存在";
        }
    }

    //更新访客信息
    public String updateVisitor(Visitor visitor){
        String pno = visitor.getPno();
        Visitor new_visitor = visitorDao.selectVisByPno(pno);
        if(new_visitor == null){
            return "用户不存在";
        }else {
            int i = visitorDao.updateVisitor(visitor);
            if (i > 0) {
                return "修改成功";
            } else {
                return "修改失败";
            }
        }
    }
}
