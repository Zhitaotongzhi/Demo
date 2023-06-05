package com.example.demo.dao;

import com.example.demo.domain.Record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("all")
@Mapper
@Repository
public interface RecordDao {
    //查询所有咨询记录
    List<Record> findAllRecord(int pageNum, int pageSize);
    long findAllRecordCount();
    //查找对应访客的咨询记录
    List<Record> findAllRecordByName(int pageNum, int pageSize, String name);
    long findAllRecordByNameCount();
    //查找对应咨询师的咨询记录
    List<Record> findAllRecordByConName(int pageNum, int pageSize, String username);
    long findAllRecordByConNameCount();
    //找到与该督导绑定的咨询师的用户名
    List<String> findAllBindByUsername(String username);
    List<Record> findAllRecordBindByUsername(int pageNum, int pageSize, List<String> list);
    long findAllBindRecordCount(List<String> list);
}
