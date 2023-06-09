package com.example.demo.service;

import com.example.demo.dao.ArrangeDao;
import com.example.demo.domain.Consultant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArrangeService {
//    @Autowired
//    ArrangeDao arrangeDao;
//    public String regSupervisorArrange(Consultant consultant) {
//        int num = arrangeObject.length;
//        for (int i = 0; i < num; i++) {
//            arrangeDao.insertSupervisorArrange(date, arrangeObject[i].getW_name(), arrangeObject[i].getW_username());
//        }
//        String username = consultant.getUsername();
//        Consultant new_consultant = adminDao.selectConByUsername(username);
//        if (new_consultant == null) {
//            int i = adminDao.insertConsultant(consultant);
//            if (i > 0) {
//                return "成功注册";
//            } else {
//                return "注册失败";
//            }
//        } else {
//            return "已存在该用户";
//        }
//    }
}
