package com.example.demo.dao;

import com.example.demo.domain.Consultant;
import com.example.demo.domain.Visitor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("all")
@Mapper
@Repository

public interface ConsultantDao {
    //所有咨询师列表
    List<Consultant> findAllConsultantByAuthorityNoName(int pageNum, int pageSize);
    //特定姓名咨询师列表
    List<Consultant> findAllConsultantByAuthority(int pageNum, int pageSize, String name);
    //统计所有咨询师数量
    long findAllConsultantCountNoName();
    //统计查询后咨询师数量
    long findAllCountCount();
    //通过username改变账号状态
    public void bannedCounselor(String username);
    public void enableCounselor(String username);
    //通过username搜索咨询师
    public Consultant selectCounselor(String username);
    //更新咨询师账号信息
    public void updateCounselorByUsername(Consultant consultant);
    //获取在线的咨询师列表
    List<Consultant> findAllConIsOnline();
    //统计在线咨询师数量
    long findAllConIsOnlineCount();
    //更新空闲忙碌状态
    public void updateConIsBusy(String username);
    public void updateConIsFree(String username);
    //通过姓名返回用户名
    public String findName(String username);
    //返回所有咨询师信息
    //List<Consultant> findAllCounselor();
    //通过username找到name
    public String findByUsername(String username);
    //找到在线的咨询师
    List<Consultant> findAllByIsBusyAndAuthority(int pageNum, int pageSize);
    //找到正在进行的咨询的总数
    int consultCount();
    //更新当前正在进行的咨询数量
    public void subCurrent(String username);
    public void addCurrent(String username);
    //督导查看与自己绑定的在线的咨询师
    List<Consultant> findAllBindIsOnline(int pageNum, int pageSize, String username);
    //结束咨询减少咨询师正在进行咨询的数量给
    public void deleteCurrent(String username);
    //删除咨询师
    public void deleteCounselor(String username);
    //为咨询师绑定督导
    public void bindSupForCounselor(String name, String supervisorUsername, String counselorUsername);
    //一个咨询师正在进行的咨询数量
    int currentCon(String username);
}
