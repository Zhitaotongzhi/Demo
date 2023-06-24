package com.example.demo.dao;

import com.example.demo.domain.Consultant;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.Supervisor;
import java.util.List;

@SuppressWarnings("all")
@Mapper
@Repository

public interface SupervisorDao {
    //通过权限返回督导列表,带有姓名
    List<Supervisor> findAllSupervisorByAuthority(int pageNum,int pageSize,String name);
    //统计所有督导数量
    long findAllSupervisorCount();
    //统计特定姓名督导
    long findAllSupervisorCountNoName();
    //通过权限返回督导，不带姓名
    List<Supervisor> findAllSupervisor(int pageNum,int pageSize);
    //禁用督导账号
    public void bannedSupervisor(String username);
    //启用督导账号
    public void enableSupervisor(String username);
    //通过用户名选择督导
    public Supervisor selectSupervisorByUsername(String username);
    //通过用户名更新督导信息
    public void updeteSupervisorByUsername(Supervisor supervisor);
    //找到所有督导
    //List<Supervisor> findAllSupervisor();
    //删除督导
    public void deleteSupervisor(String username);
    //正在进行的求助数
    public int assistCount();
    //在线的督导列表
    List<Supervisor> findAllSupIsonline(int pageNum, int pageSize);
    long onLineCount();
    //更新正在进行的求助数量
    public void addCurrentAssist(String username);
    public void subCurrentAssist(String username);
    //一个督导总求助数量
    int totalCount(String username);

    //咨询师求助督导时看到的督导列表
    Supervisor BindSupIsonline(String username);
    public String bindUsername2Username(String username);

    //与某督导绑定的在线的咨询师
    List<Consultant> findBindCounselorIsonline(int pageNum, int pageSize, String username);
    //更新督导今日求助数和求助总数
    public void updateOrderAndTotalOrder(String username);
}
