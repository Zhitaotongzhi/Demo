package com.example.demo.dao;

import com.example.demo.domain.Assist;
import com.example.demo.domain.Record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
@SuppressWarnings("all")
public interface AssistDao {
    public int insertAssist(Assist assist);
    //返回所有求助记录
    List<Assist> findAllAssist(int pageNum, int pageSize);
    long totalAll();
    //通过咨询师姓名查找求助记录
    List<Assist> findAllAssistByConName(int pageNum, int pageSize, String counselorName);
    long totalByConName(String counselorName);
    //通过督导姓名查询求助记录
    List<Assist> findAllAssistBySupName(int pageNum, int pageSize, String supervisorName);



    //通过督导username查询求助记录
    List<Assist> findAllBindAssist(int pageNum, int pageSize, String supervisorUsername);

    //找到与该督导绑定的咨询师的用户名
    List<String> findAllBindByUsername(String supervisorUsername);

    //督导通过姓名查看与自己绑定的咨询师的求助记录
    List<Assist> findAllBindAssistByConName(int pageNum, int pageSize, String name, List<String> list);

    //督导通过时间查询与自己绑定的咨询师的咨询记录
    List<Assist> findAllBindAssistByTime(int pageNum, int pageSize, String startDate, String endDate, List<String> list);

    //两个查询条件都有
    List<Assist> findAllBindAssistByAll(int pageNum, int pageSize, String counselorName, String startDate, String endDate);

    //咨询师查看自己的求助记录
    List<Assist> findAllOwnAssist(int pageNum, int pageSize, String username);

    //咨询师通过时间查询自己的咨询记录
    List<Assist> findAllOwnAssistByTime(int pageNum, int pageSize,String username, String startDate, String endDate);


    //通过时间查询求助记录
    List<Assist> findAllAssistByTime(int pageNum, int pageSize, String startDate, String endDate);
    long totalByTime(String startDate, String endDate);
    //通过所有条件查询求助记录
    List<Assist> findAllAssistByAll(int pageNum, int pageSize, String counselorName, String startDate, String endDate);
    long totalByAll(String counselorName, String startDate, String endDate);

    public Date supervisorTodayTotalTime(String supervisorUsername, String currentDate);

    public int supervisorTodayAssistCount(String supervisorUsername, String currentDate);

    public List<Assist> currentAssist(int limit, String username);


}
