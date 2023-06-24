package com.example.demo.dao;

import com.example.demo.domain.Assist;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
    //通过时间查询求助记录
    List<Assist> findAllAssistByTime(int pageNum, int pageSize, String startDate, String endDate);
    long totalByTime(String startDate, String endDate);
    //通过所有条件查询求助记录
    List<Assist> findAllAssistByAll(int pageNum, int pageSize, String counselorName, String startDate, String endDate);
    long totalByAll(String counselorName, String startDate, String endDate);
}
