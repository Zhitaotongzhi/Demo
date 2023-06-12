package com.example.demo.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.example.demo.dao.AdminDao;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.domain.Consultant;
import com.example.demo.dao.ConsultantDao;
import com.example.demo.service.ConsultantService;
import com.example.demo.service.AdminService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/counselors")
public class ConsultantController {
    @Autowired
    private ConsultantDao consultantDao;

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private AdminService adminService;

    //查询咨询师列表
    @GetMapping("/list")
    public Result conselorList(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam String search){
        int i = Integer.parseInt(pageNum);
        int j = Integer.parseInt(pageSize);
        if(search.isEmpty()){
            List<Consultant> list = consultantDao.findAllConsultantByAuthorityNoName((i - 1) * j, j);
            long total = consultantDao.findAllConsultantCountNoName();
            Map<String, Object> counselor = new HashMap<>();
            counselor.put("total",total);
            counselor.put("Counselors",list);
            return Result.success(counselor);
        }else{
            List<Consultant> list = consultantDao.findAllConsultantByAuthority((i - 1) * j, j, search);
            long total = consultantDao.findAllCountCount();
            Map<String, Object> counselor = new HashMap<>();
            counselor.put("total",total);
            counselor.put("Counselors",list);
            return Result.success(counselor);
        }
    }

    //完整返回信息的注册咨询师
    @PostMapping("/insert")
    public Result addConsultant(@RequestBody Consultant consultant){
        String flag = adminService.regCounselor(consultant);
        if(flag.equals("成功注册")){
            return Result.success();
        }else if(flag.equals("已存在该用户")){
            return Result.failure("20005","用户已存在");
        }else{
            return Result.failure("2","操作失败");
        }
    }

    //禁用咨询师账号
    @PatchMapping("/banned/{username}")
    public Result bannedCounselor(@PathVariable String username){
        Consultant consultant = consultantDao.selectCounselor(username);
        consultantDao.bannedCounselor(username);
        return Result.success();
    }

    //启用咨询师账号
    @PatchMapping("/enable/{username}")
    public Result enableCounselor(@PathVariable String username){
        Consultant consultant = consultantDao.selectCounselor(username);
        consultantDao.enableCounselor(username);
        return Result.success();
    }

    //更新咨询师账号信息
    @PatchMapping("/update")
    public Result updateCounselor(@RequestBody Consultant consultant){
        consultantDao.updateCounselorByUsername(consultant);
        return Result.success();
    }

    //查看在线咨询师列表
    @GetMapping("/online/list")
    public Result findAllConIsOnline(){
        List<Consultant> list = consultantDao.findAllConIsOnline();
        long total = consultantDao.findAllConIsOnlineCount();
        Map<String, Object> counselor = new HashMap<>();
        counselor.put("total", total);
        counselor.put("counselors", list);
        return Result.success(counselor);
    }
    //更新忙闲状态
    @PostMapping("/updateIsBusy")
    public Result updateIsBusy(@RequestParam String username){
        consultantDao.updateConIsBusy(username);
        return Result.success();
    }
    @PostMapping("/updateIsFree")
    public Result updateIsFree(@RequestParam String username){
        consultantDao.updateConIsFree(username);
        return Result.success();
    }
    //Web首页显示所有在线的咨询师
    @GetMapping("/onlineList")
    public Result ShowIsOnline(@RequestParam String pageNum, @RequestParam String pageSize){
        int i = Integer.parseInt(pageNum);
        int j = Integer.parseInt(pageSize);
        List<Consultant> consultants = consultantDao.findAllByIsBusyAndAuthority((i - 1) * j, j);
        long total = consultants.size();
        Map<String, Object> counselor = new HashMap<>();
        counselor.put("total", total);
        counselor.put("counselors", consultants);
        return Result.success(counselor);
    }
    //更新咨询师当前的咨询数量
    @GetMapping("/addCurrent/{username}")
    public void updateCurrent(@PathVariable String username){
        consultantDao.addCurrent(username);
    }
    @GetMapping("/subCurrent/{username}")
    public void subCurrent(@PathVariable String username){
        consultantDao.subCurrent(username);
    }
    //正在进行的咨询数
    @GetMapping("/consultCount")
    public Result total(){
        int total = consultantDao.consultCount();
        Map<String, Object> totals = new HashMap<>();
        totals.put("total", total);
        return Result.success(totals);
    }
}
