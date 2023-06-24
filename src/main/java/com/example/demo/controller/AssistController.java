package com.example.demo.controller;

import com.example.demo.dao.AssistDao;
import com.example.demo.domain.Assist;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assist")
@CrossOrigin
public class AssistController {
    @Autowired
    private AssistDao assistDao;

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
}
