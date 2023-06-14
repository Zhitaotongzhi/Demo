package com.example.demo.dao;

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
}
