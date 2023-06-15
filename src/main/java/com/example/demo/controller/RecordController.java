package com.example.demo.controller;

import com.example.demo.dao.ConsultantDao;
import com.example.demo.domain.Record;
import com.example.demo.dao.RecordDao;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@RestController
@RequestMapping("/records")
@CrossOrigin
public class RecordController {
    @Autowired
    private RecordDao recordDao;

    @Autowired
    private ConsultantDao consultantDao;



    //所有咨询记录
    @GetMapping("/list")
    public Result recordList(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam String counselorName, @RequestParam String visitorName, @RequestParam String startDate, @RequestParam String endDate){
        int i = Integer.parseInt(pageNum);
        int j = Integer.parseInt(pageSize);
        if(counselorName.isEmpty() && visitorName.isEmpty() && startDate.isEmpty()){
            List<Record> records = recordDao.findAllRecord((i - 1) * j, j);
            long total = recordDao.findAllRecordCount();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(counselorName.isEmpty() && startDate.isEmpty() && !visitorName.isEmpty()){
            List<Record> records = recordDao.findAllRecordByName((i - 1) * j, j, visitorName);
            long total = recordDao.findAllRecordByNameCount();
            Map<String, Object> record = new HashMap<>();
            record.put("total", records.size());
            record.put("records", records);
            return Result.success(record);
        }else if(visitorName.isEmpty() && !counselorName.isEmpty() && startDate.isEmpty()){
            List<Record> records = recordDao.findAllRecordByConName((i - 1) * j, j, counselorName);
            long total = recordDao.findAllRecordByConNameCount();
            Map<String, Object> record = new HashMap<>();
            record.put("total", records.size());
            record.put("records", records);
            return Result.success(record);
        }else if(!visitorName.isEmpty() && !counselorName.isEmpty() && startDate.isEmpty()){
            List<Record> records = recordDao.findAllRecordByNameAndUserame((i - 1) * j, j, visitorName, counselorName);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(visitorName.isEmpty() && counselorName.isEmpty() && !startDate.isEmpty()){
            List<Record> records = recordDao.findAllRecordByTime((i - 1) * j, j, startDate, endDate);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(!visitorName.isEmpty() && !counselorName.isEmpty()  && !startDate.isEmpty()){
            List<Record> records = recordDao.findAllRecordByAll((i - 1) * j, j, visitorName, counselorName, startDate, endDate);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(!visitorName.isEmpty() && counselorName.isEmpty() && !startDate.isEmpty()){
            List<Record> records = recordDao.findAllRecordByNameAndTime((i - 1) * j, j, visitorName, startDate, endDate);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(visitorName.isEmpty() && !counselorName.isEmpty() && !startDate.isEmpty()){
            List<Record> records = recordDao.findAllRecordByUsernameAndTime((i - 1) * j, j, counselorName, startDate, endDate);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else{
            return Result.failure("2", "操作失败");
        }
    }

    //查询与该督导绑定的咨询师的咨询记录
    @GetMapping("/listForSupervisor/{supervisorUsername}")
    public Result bindList(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam String visitorName, @RequestParam String counselorName, @PathVariable String supervisorUsername, @RequestParam String startDate, @RequestParam String endDate){
        int i = Integer.parseInt(pageNum);
        int j = Integer.parseInt(pageSize);
        List<String> list = recordDao.findAllBindByUsername(supervisorUsername);
        System.out.println(list);
        if(visitorName.isEmpty() && counselorName.isEmpty() && startDate.isEmpty()){
            List<Record> records = recordDao.findAllRecordBindByUsername((i - 1) * j, j, list);
            System.out.println(records);
            long total = recordDao.findAllBindRecordCount(list);
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(!visitorName.isEmpty() && counselorName.isEmpty() && startDate.isEmpty()){
            List<Record> records = recordDao.findAllBindRecordByName((i - 1) * j, j, visitorName, list);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(visitorName.isEmpty() && !counselorName.isEmpty() && startDate.isEmpty()){
            List<Record> records = recordDao.findAllRecordByConName((i - 1) * j, j, counselorName);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(!visitorName.isEmpty() && !counselorName.isEmpty() && startDate.isEmpty()){
            List<Record> records = recordDao.findAllRecordByNameAndUserame((i - 1) * j, j, visitorName, counselorName);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(visitorName.isEmpty() && counselorName.isEmpty() && !startDate.isEmpty()){
            List<Record> records = recordDao.findAllBindRecordByTime((i - 1) * j, j, startDate, endDate, list);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(!visitorName.isEmpty() && !counselorName.isEmpty()  && !startDate.isEmpty()){
            List<Record> records = recordDao.findAllRecordByAll((i - 1) * j, j, visitorName, counselorName, startDate, endDate);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else{
            return Result.failure("2", "操作失败");
        }
    }

    //咨询师查看自己的咨询记录
    @GetMapping("/listForCounselor/{username}")
    public Result selfRecord(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam String visitorName, @RequestParam String startDate, @RequestParam String endDate, @PathVariable String username){
        int i = Integer.parseInt(pageNum);
        int j = Integer.parseInt(pageSize);
        String counselorName = consultantDao.findName(username);
        if(visitorName.isEmpty() && startDate.isEmpty()){
            List<Record> records = recordDao.findAllSelfRecord((i - 1) * j, j, counselorName);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(!visitorName.isEmpty() && startDate.isEmpty()){
            List<Record> records = recordDao.findAllSelfRecordByName((i - 1) * j, j, counselorName, visitorName);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(visitorName.isEmpty() && !startDate.isEmpty()){
            List<Record> records = recordDao.findAllSelfRecordByTime((i - 1) * j, j, counselorName, startDate, endDate);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(!visitorName.isEmpty() && !startDate.isEmpty()){
            List<Record> records = recordDao.findAllSelfRecordByAll((i - 1) * j, j, counselorName, visitorName, startDate, endDate);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else{
            return Result.failure("2", "操作失败");
        }
    }


    //访客查看自己的咨询记录
    @GetMapping("/recordForVisitor/{pno}")
    public Result findAllRecord(@PathVariable String pno){
        List<Record> records = recordDao.findAllVisitorRecordByPno(pno);
        long total = records.size();
        Map<String, Object> record = new HashMap<>();
        record.put("total", total);
        record.put("records", records);
        return Result.success(record);
    }

    //插入新的咨询记录
    @PostMapping("/insert")
    public Result insertRecord(@RequestBody Record record){
        int i = recordDao.insertRecord(record);
        if(i > 0){
            return Result.success();
        }else{
            return Result.failure("2", "操作失败");
        }
    }

    //统计今天总的咨询时长
    @GetMapping("/totalTime")
    public Result totalTime(){
        LocalDate date = LocalDate.now();
        Date totalTime = recordDao.totalTimeCount(date.toString());
        Map<String, Object> total = new HashMap<>();
        total.put("totalTime", totalTime);
        return Result.success(total);
    }
    //咨询师今日咨询总时长
    @GetMapping("/counselorTotalTime/{username}")
    public Result conTotalTime(@PathVariable String username){
        LocalDate date = LocalDate.now();
        String name = consultantDao.findByUsername(username);
        Date totalTime = recordDao.counselotTotalTime(name, date.toString());
        Map<String, Object> total = new HashMap<>();
        total.put("totalTime", totalTime);
        return Result.success(total);
    }
    //今日统计总数
    @GetMapping("/totalCount")
    public Result totalCount(){
        LocalDate date = LocalDate.now();
        int total = recordDao.totalCount(date.toString());
        Map<String, Object> totals = new HashMap<>();
        totals.put("total", total);
        return Result.success(totals);
    }
    //咨询师今日咨询总数
    @GetMapping("/counselorTotalCount/{username}")
    public Result conTotalCount(@PathVariable String username){
        LocalDate date = LocalDate.now();
        String counselorName = consultantDao.findName(username);
        int total = recordDao.counselorRecordCount(counselorName, date.toString());
        Map<String, Object> totals = new HashMap<>();
        totals.put("total", total);
        return Result.success(totals);
    }
    //显示最近的咨询记录
    @GetMapping("/currentRecords")
    public Result currentRecord(){
        List<Record> records = recordDao.currentRecord(5);
        Map<String, Object> record = new HashMap<>();
        record.put("records", records);
        return Result.success(record);
    }
    //正在进行的咨询总数
    @GetMapping("/currentCount")
    public Result currentCount(){
        long total = recordDao.currentCount();
        Map<String, Object> count = new HashMap<>();
        count.put("total", total);
        return Result.success(count);
    }
}
