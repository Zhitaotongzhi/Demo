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
        }else{
            return Result.failure("2", "操作失败");
        }
    }

    //查询与该督导绑定的咨询师的咨询记录
    @GetMapping("/supervisorList/{username}")
    public Result bindList(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam String search, @PathVariable String username){
        int i = Integer.parseInt(pageNum);
        int j = Integer.parseInt(pageSize);
        if(search.isEmpty()){
            List<String> list = recordDao.findAllBindByUsername(username);
            System.out.println(list);
            List<Record> records = recordDao.findAllRecordBindByUsername((i - 1) * j, j, list);
            System.out.println(records);
            long total = recordDao.findAllBindRecordCount(list);
            Map<String, Object> map = new HashMap<>();
            map.put("total", total);
            map.put("records", records);
            return Result.success(map);
        }else{
            return Result.failure("2", "操作失败");
        }
    }
}
