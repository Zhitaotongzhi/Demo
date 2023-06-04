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

    public boolean login(Worker worker){
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
    }
}
