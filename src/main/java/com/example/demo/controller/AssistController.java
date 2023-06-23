package com.example.demo.controller;

import com.example.demo.dao.AssistDao;
import com.example.demo.domain.Assist;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
