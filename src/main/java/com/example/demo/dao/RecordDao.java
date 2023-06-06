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
    //查找访客一段时间内的咨询记录
    List<Record> findAllRecordByNameAndTime(int pageNum, int pageSize, String name, String startTime, String endTime);
    //查找咨询师一段时间内的咨询记录
    List<Record> findAllRecordByUsernameAndTime(int pageNum, int pageSize, String username, String startTime, String endTime);
    //所有条件全有
    List<Record> findAllRecordByAll(int pageNum, int pageSize, String name, String username, String startTime, String endTime);
    //督导通过姓名查看与自己绑定的咨询师的咨询师的咨询记录
    List<Record> findAllBindRecordByName(int pageNum, int pageSize, String name, List<String> list);
    //督导通过姓名和用户名查找与自己绑定的咨询师的咨询记录
    List<Record> findAllBindRecordByNameAndUsername(int pageNum, int pageSize, String name, String username);
    //督导通过时间查询与自己绑定的咨询师的咨询记录
    List<Record> findAllBindRecordByTime(int pageNum, int pageSize, String startTime, String endTime, List<String> list);
    //返回一位用户最后一次咨询的日期
    public String findLatestTime(String name);
    //通过日期返回评价
    public String findEvaluate(String time);
    //咨询师查询的咨询记录
    //咨询师查看的全部的咨询记录
    List<Record> findAllSelfRecord(int pageNum, int pageSize, String counselorName);
    //通过访客姓名查询
    List<Record> findAllSelfRecordByName(int pageNum, int pageSize, String counselorName, String visitorName);
    //通过时间查询
    List<Record> findAllSelfRecordByTime(int pageNum, int pageSize, String counselorName, String startTime, String endTime);
    //通过所有条件查询
    List<Record> findAllSelfRecordByAll(int pageNum, int pageSize, String counselorName, String visitorName, String startTime, String endTime);
    //访客查看自己的咨询记录
    List<Record> findAllVisitorRecordByPno(String pno);
    //插入咨询记录
    public int insertRecord(Record record);

}
