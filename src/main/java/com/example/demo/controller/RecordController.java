package com.example.demo.controller;

import com.example.demo.domain.Record;
import com.example.demo.dao.RecordDao;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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



    //所有咨询记录
    @GetMapping("/list")
    public Result recordList(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam String username, @RequestParam String name, @RequestParam String startTime, @RequestParam String endTime){
        int i = Integer.parseInt(pageNum);
        int j = Integer.parseInt(pageSize);
        if(username.isEmpty() && name.isEmpty() && startTime.isEmpty()){
            List<Record> records = recordDao.findAllRecord((i - 1) * j, j);
            long total = recordDao.findAllRecordCount();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(username.isEmpty() && startTime.isEmpty() && !name.isEmpty()){
            List<Record> records = recordDao.findAllRecordByName((i - 1) * j, j, name);
            long total = recordDao.findAllRecordByNameCount();
            Map<String, Object> record = new HashMap<>();
            record.put("total", records.size());
            record.put("records", records);
            return Result.success(record);
        }else if(name.isEmpty() && !username.isEmpty() && startTime.isEmpty()){
            List<Record> records = recordDao.findAllRecordByConName((i - 1) * j, j, username);
            long total = recordDao.findAllRecordByConNameCount();
            Map<String, Object> record = new HashMap<>();
            record.put("total", records.size());
            record.put("records", records);
            return Result.success(record);
        }else if(!name.isEmpty() && !username.isEmpty() && startTime.isEmpty()){
            List<Record> records = recordDao.findAllRecordByNameAndUserame((i - 1) * j, j, name, username);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(name.isEmpty() && username.isEmpty() && !startTime.isEmpty()){
            List<Record> records = recordDao.findAllRecordByTime((i - 1) * j, j, startTime, endTime);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(!name.isEmpty() && !username.isEmpty()  && !startTime.isEmpty()){
            List<Record> records = recordDao.findAllRecordByAll((i - 1) * j, j, name, username, startTime, endTime);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(!name.isEmpty() && username.isEmpty() && !startTime.isEmpty()){
            List<Record> records = recordDao.findAllRecordByNameAndTime((i - 1) * j, j, name, startTime, endTime);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(name.isEmpty() && !username.isEmpty() && !startTime.isEmpty()){
            List<Record> records = recordDao.findAllRecordByUsernameAndTime((i - 1) * j, j, username, startTime, endTime);
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
    public Result bindList(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam String name, @RequestParam String username, @PathVariable String supervisorUsername, @RequestParam String startTime, @RequestParam String endTime){
        int i = Integer.parseInt(pageNum);
        int j = Integer.parseInt(pageSize);
        List<String> list = recordDao.findAllBindByUsername(supervisorUsername);
        System.out.println(list);
        if(name.isEmpty() && username.isEmpty() && startTime.isEmpty()){
            List<Record> records = recordDao.findAllRecordBindByUsername((i - 1) * j, j, list);
            System.out.println(records);
            long total = recordDao.findAllBindRecordCount(list);
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(!name.isEmpty() && username.isEmpty() && startTime.isEmpty()){
            List<Record> records = recordDao.findAllBindRecordByName((i - 1) * j, j, name, list);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(name.isEmpty() && !username.isEmpty() && startTime.isEmpty()){
            List<Record> records = recordDao.findAllRecordByConName((i - 1) * j, j, username);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(!name.isEmpty() && !username.isEmpty() && startTime.isEmpty()){
            List<Record> records = recordDao.findAllRecordByNameAndUserame((i - 1) * j, j, name, username);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(name.isEmpty() && username.isEmpty() && !startTime.isEmpty()){
            List<Record> records = recordDao.findAllBindRecordByTime((i - 1) * j, j, startTime, endTime, list);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else if(!name.isEmpty() && !username.isEmpty()  && !startTime.isEmpty()){
            List<Record> records = recordDao.findAllRecordByAll((i - 1) * j, j, name, username, startTime, endTime);
            long total = records.size();
            Map<String, Object> record = new HashMap<>();
            record.put("total", total);
            record.put("records", records);
            return Result.success(record);
        }else{
            return Result.failure("2", "操作失败");
        }
    }
}
