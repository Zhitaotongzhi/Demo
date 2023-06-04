package com.example.demo.dao;

import com.example.demo.domain.Admin;
import com.example.demo.domain.Consultant;
import com.example.demo.domain.Supervisor;
import com.example.demo.domain.Visitor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Mapper
@Repository

public interface AdminDao {
    //通过用户名查询用于管理员登录
    public Admin selectAdByUsername(String username);
    //查看所有访客
    List<Visitor> findAllVisitorByPage(int pageNum,int pageSize);
    long findVisitorCount();


    //注册新的咨询师或督导
    public int insertConsultant(Consultant consultant);
    public int insertSupervisor(Supervisor supervisor);

    //通过username确定权限
    public String selectAuByUsername(String username);

    //更新访客账号状态
    public  void bannedByPno(String pno);
    public  void enableByPno(String pno);

    //更新员工账号状态
    public Admin updateByUsername(String username);

    //更新咨询师信息
    public Admin updateConByUsername(Consultant consultant);

    //更新督导信息
    public Admin updaetSupByUsername(Supervisor supervisor);

    //通过电话号码查询访客
    public Visitor selectByPno(String pno);

    //通过用户名查找咨询师
    public Consultant selectConByUsername(String username);

    //通过用户名查找督导
    public Supervisor selectSupByUsername(String username);
}
