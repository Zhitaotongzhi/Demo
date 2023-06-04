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
