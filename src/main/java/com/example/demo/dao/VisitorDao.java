package com.example.demo.dao;

import com.example.demo.domain.Visitor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings({"all"})
@Mapper
@Repository


public interface VisitorDao {
    //通过姓名查询
    public Visitor selectVisByPno(String pno);
    //插入访客数据
    public int insertVisitor(Visitor visitor);

    List<Visitor> findallVisitor(int pageNum,int pageSize,String name);

    long findVisitorCount();

    public int updateVisitor(Visitor visitor);
}
