package com.example.demo.dao;

import com.example.demo.domain.Arrange;
import com.example.demo.domain.ArrangeElement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArrangeDao {
    public List<ArrangeElement> selectCounselorArrangeByDate(String date);
    public List<ArrangeElement> selectSupervisorArrangeByDate(String date);

    public void insertSupervisorArrange(String date, String w_name, String w_username);

    public void insertCounselorArrange(String date, String w_name, String w_username);

    public void deleteSupervisorArrange(String date, String w_name, String w_username);

    public void deleteCounselorArrange(String date, String w_name, String w_username);

    public List<ArrangeElement> selectAvailableSupervisorByDate(String date);

    public List<ArrangeElement> selectAvailableCounselorByDate(String date);

    public List<String> selectSchedule(String w_username, String month);
}
