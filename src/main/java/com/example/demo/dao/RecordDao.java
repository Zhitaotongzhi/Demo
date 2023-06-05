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
    //通过咨询师用户名和访客姓名找到咨询记录
    List<Record> findAllRecordByNameAndUserame(int pageNum, int pageSize, String name, String username);
    //根据时间范围查找
    List<Record> findAllRecordByTime(int pageNum, int pageSize, String startTime, String endTime);
    //所有条件全有
    List<Record> findAllRecordByAll(int pageNum, int pageSize, String name, String username, String startTime, String endTime);

    //督导通过姓名查看与自己绑定的咨询师的咨询师的咨询记录
    List<Record> findAllBindRecordByName(int pageNum, int pageSize, String name, List<String> list);
    //督导通过姓名和用户名查找与自己绑定的咨询师的咨询记录
    List<Record> findAllBindRecordByNameAndUsername(int pageNum, int pageSize, String name, String username);
    //督导通过时间查询与自己绑定的咨询师的咨询记录
    List<Record> findAllBindRecordByTime(int pageNum, int pageSize, String startTime, String endTime, List<String> list);
}
