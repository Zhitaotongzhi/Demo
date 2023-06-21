package com.example.demo.service;


import com.example.demo.dao.WorkerDao;
import com.example.demo.domain.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("all")
@Service
public class WorkerService {
    @Autowired
    WorkerDao workerDao;

    /*public boolean login(Worker worker){
        String loginUsername = worker.getUsername();
        String loginPassword = worker.getPassword();
        Worker loginWorker = workerDao.selectWorkerByUsername(loginUsername);
        if(loginWorker == null){
            return false;
        }else if(loginWorker.getPassword().equals(loginPassword)){
            return true;
        }else{
            return false;
        }
    }*/

    public String login(Worker worker){
        String loginUsername = worker.getUsername();
        String loginPassword = worker.getPassword();
        Worker loginWorker = workerDao.selectWorkerByUsername(loginUsername);
        String is_online = workerDao.workerIsOnline(loginUsername);
//        if(is_online.equals("1") && loginWorker != null){
//            return "该用户已登录";
//        }else if(loginWorker == null && is_online.equals("0")){
//            return "该用户不存在";
//        }else if(is_online.equals("0") && loginWorker.getPassword().equals(loginPassword)){
//            return "登录成功";
//        }else{
//            return "登录出错";
//        }
       if(loginWorker == null ){
            return "该用户不存在";
        }else if(loginWorker.getPassword().equals(loginPassword)){
            return "登录成功";
        }else{
            return "登录出错";
        }
    }
}
