package com.example.demo.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.example.demo.dao.ArrangeDao;
import com.example.demo.domain.Arrange;
import com.example.demo.domain.ArrangeElement;
import com.example.demo.domain.Consultant;
import com.example.demo.service.ArrangeService;
import com.example.demo.utils.Result;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin
@RequestMapping("/arrange")
public class ArrangeController {
    @Autowired
    private ArrangeDao arrangeDao;

    @Autowired
    private ArrangeService arrangeService;

    //查询月排班
    @GetMapping("/list")
    public Result arrangeList(@RequestParam String year, @RequestParam String month){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.valueOf(year));
        cal.set(Calendar.MONTH, (Integer.valueOf(month) - 1));
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        int dayNum = cal.getActualMaximum(Calendar.DATE);
        List<Map> arrangeData = new ArrayList<Map>();
        for (int i = 1; i<dayNum; i++){
            String date = year + '-' + month + '-' + Integer.toString(i);
            List<ArrangeElement> counselorList = arrangeDao.selectCounselorArrangeByDate(date);
            List<ArrangeElement> supervisorList = arrangeDao.selectSupervisorArrangeByDate(date);
            Map<String, Object> dateArrange = new HashMap<>();
            dateArrange.put("date",date);
            dateArrange.put("counselor",counselorList);
            dateArrange.put("supervisor",supervisorList);
            arrangeData.add(dateArrange);
        }
        Map<String, Object> arrange = new HashMap<>();
        arrange.put("arrangeData", arrangeData);
        return Result.success(arrange);
    }

    @PostMapping("/insertSupervisor")
    public Result addSupervisor(@RequestParam String date, @RequestBody ArrangeElement[] supervisors){
        int num = supervisors.length;
        for(int i = 0; i < num; i++){
            arrangeDao.insertSupervisorArrange(date, supervisors[i].getW_name(), supervisors[i].getW_username());
        }
        return Result.success();
    }

    @PostMapping("/insertCounselor")
    public Result addCounselor(@RequestParam String date, @RequestBody ArrangeElement[] counselors){
        int num = counselors.length;
        for(int i = 0; i < num; i++){
            arrangeDao.insertCounselorArrange(date, counselors[i].getW_name(), counselors[i].getW_username());
        }
        return Result.success();
    }

    @PostMapping("/deleteSupervisor")
    public Result removeSupervisor(@RequestParam String date, @RequestBody ArrangeElement supervisor){
        arrangeDao.deleteSupervisorArrange(date, supervisor.getW_name(), supervisor.getW_username());
        return Result.success();
    }

    @PostMapping("/deleteCounselor")
    public Result removeCounselor(@RequestParam String date, @RequestBody ArrangeElement counselor){
        arrangeDao.deleteCounselorArrange(date, counselor.getW_name(), counselor.getW_username());
        return Result.success();
    }


    @GetMapping("/availableSupervisor")
    public Result getAvailableSupervisor(@RequestParam String date){
        List<ArrangeElement> availableSupervisorList = arrangeDao.selectAvailableSupervisorByDate(date);
        Map<String, Object> available = new HashMap<>();
        available.put("availableSupervisorData", availableSupervisorList);
        return Result.success(available);
    }

    @GetMapping("/availableCounselor")
    public Result getAvailableCounselor(@RequestParam String date){
        List<ArrangeElement> availableCounselorList = arrangeDao.selectAvailableCounselorByDate(date);
        Map<String, Object> available = new HashMap<>();
        available.put("availableCounselorData", availableCounselorList);
        return Result.success(available);
    }

    @GetMapping("/schedule")
    public Result getOwnSchedule(@RequestParam String name){
        String month = "6";
        List<String> dateList = arrangeDao.selectSchedule(name, month);
        Map<String, Object> available = new HashMap<>();
        available.put("dateList", dateList);
        return Result.success(available);
    }
}
