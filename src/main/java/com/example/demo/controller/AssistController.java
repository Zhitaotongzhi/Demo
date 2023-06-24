package com.example.demo.controller;

import com.example.demo.dao.AssistDao;
import com.example.demo.dao.ConsultantDao;
import com.example.demo.dao.SupervisorDao;
import com.example.demo.domain.Assist;
import com.example.demo.domain.Record;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assist")
@CrossOrigin
public class AssistController {
    @Autowired
    private AssistDao assistDao;

    @Autowired
    private SupervisorDao supervisorDao;

    @Autowired
    private ConsultantDao consultantDao;

    @PostMapping("/insert")
    public Result insertAssist(@RequestBody Assist assist){
        int i = assistDao.insertAssist(assist);
        if(i > 0){
            return Result.success();
        }else{
            return Result.failure("2", "操作失败");
        }
    }

    //管理员查看求助记录
    @GetMapping("/list")
    public Result list(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam String counselorName, @RequestParam String startDate, @RequestParam String endDate){
        int i = Integer.parseInt(pageNum);
        int j = Integer.parseInt(pageSize);
        if(counselorName.isEmpty() && startDate.isEmpty()){
            List<Assist> assists = assistDao.findAllAssist((i - 1) * j, j);
            long total = assistDao.totalAll();
            Map<String, Object> maps = new HashMap<>();
            maps.put("total", total);
            maps.put("assists", assists);
            return Result.success(maps);
        }else if(!counselorName.isEmpty()  && startDate.isEmpty()){
            List<Assist> assists = assistDao.findAllAssistByConName((i - 1) * j, j, counselorName);
            long total = assistDao.totalByConName(counselorName);
            Map<String, Object> maps = new HashMap<>();
            maps.put("total", total);
            maps.put("assists", assists);
            return Result.success(maps);
        }
        else if(counselorName.isEmpty()  && !startDate.isEmpty()){
            List<Assist> assists = assistDao.findAllAssistByTime((i - 1) * j, j, startDate, endDate);
            long total = assistDao.totalByTime(startDate, endDate);
            Map<String, Object> maps = new HashMap<>();
            maps.put("total", total);
            maps.put("assists", assists);
            return Result.success(maps);
        }else if(!counselorName.isEmpty() && !startDate.isEmpty()){
            List<Assist> assists = assistDao.findAllAssistByAll((i - 1) * j, j, counselorName, startDate, endDate);
            long total = assistDao.totalByAll(counselorName, startDate, endDate);
            Map<String, Object> maps = new HashMap<>();
            maps.put("total", total);
            maps.put("assists", assists);
            return Result.success(maps);
        }else{
            return Result.failure("2", "操作失败");
        }
    }

    //督导查看其下咨询师的求助记录
    @GetMapping("/bindList/{supervisorUsername}")
    public Result bindAssistList(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam String counselorName, @PathVariable String supervisorUsername, @RequestParam String startDate, @RequestParam String endDate){
        int i = Integer.parseInt(pageNum);
        int j = Integer.parseInt(pageSize);
        List<String> list = assistDao.findAllBindByUsername(supervisorUsername);
        if(counselorName.isEmpty() && startDate.isEmpty()){
            List<Assist> assists = assistDao.findAllBindAssist((i - 1) * j, j, supervisorUsername);
            long total = assists.size();
            Map<String, Object> maps = new HashMap<>();
            maps.put("total", total);
            maps.put("assists", assists);
            return Result.success(maps);
        }else if(!counselorName.isEmpty()  && startDate.isEmpty()){
            List<Assist> assists = assistDao.findAllBindAssistByConName((i - 1) * j, j, counselorName, list);
            long total = assistDao.totalByConName(counselorName);
            Map<String, Object> maps = new HashMap<>();
            maps.put("total", total);
            maps.put("assists", assists);
            return Result.success(maps);
        }
        else if(counselorName.isEmpty()  && !startDate.isEmpty()){
            List<Assist> assists = assistDao.findAllBindAssistByTime((i - 1) * j, j, startDate, endDate, list);
            long total = assistDao.totalByTime(startDate, endDate);
            Map<String, Object> maps = new HashMap<>();
            maps.put("total", total);
            maps.put("assists", assists);
            return Result.success(maps);
        }else if(!counselorName.isEmpty() && !startDate.isEmpty()){
            List<Assist> assists = assistDao.findAllBindAssistByAll((i - 1) * j, j, counselorName, startDate, endDate);
            long total = assistDao.totalByAll(counselorName, startDate, endDate);
            Map<String, Object> maps = new HashMap<>();
            maps.put("total", total);
            maps.put("assists", assists);
            return Result.success(maps);
        }else{
            return Result.failure("2", "操作失败");
        }
    }

//    //统计今天总的求助时长
//    @GetMapping("/totalTime")
//    public Result totalTime(){
//        LocalDate date = LocalDate.now();
//        Date totalTime = assistDao.totalTimeCount(date.toString());
//        Map<String, Object> total = new HashMap<>();
//        total.put("totalTime", totalTime);
//        return Result.success(total);
//    }

    //督导今日响应求助总时长
    @GetMapping("/supervisorTodayAssistTime/{supervisorUsername}")
    public Result assistTotalTime(@PathVariable String supervisorUsername){
        LocalDate date = LocalDate.now();
        System.out.println(date);
        Date totalTime = assistDao.supervisorTodayTotalTime(supervisorUsername, date.toString());
        Map<String, Object> total = new HashMap<>();
        total.put("totalTime", totalTime);
        return Result.success(total);
    }

//    //今日统计求助总数
//    @GetMapping("/totalCount")
//    public Result totalCount(){
//        LocalDate date = LocalDate.now();
//        int total = assistDao.totalCount(date.toString());
//        Map<String, Object> totals = new HashMap<>();
//        totals.put("total", total);
//        return Result.success(totals);
//    }


    //督导今日响应求助总数
    @GetMapping("/supervisorTodayAssistCount/{supervisorUsername}")
    public Result assistTotalCount(@PathVariable String supervisorUsername){
        LocalDate date = LocalDate.now();
        int total = assistDao.supervisorTodayAssistCount(supervisorUsername, date.toString());
        Map<String, Object> totals = new HashMap<>();
        totals.put("total", total);
        return Result.success(totals);
    }

    //显示最近的求助记录
    @GetMapping("/currentRecords")
    public Result currentRecord(){
        List<Assist> assists = assistDao.currentAssist(5);
        Map<String, Object> assist = new HashMap<>();
        assist.put("assists", assists);
        return Result.success(assist);
    }

}
