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
}
