package com.example.demo.dao;

import com.example.demo.domain.Worker;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Bind;

import java.util.List;

@SuppressWarnings("all")
@Mapper
@Repository
public interface WorkerDao {
    public String selectAuthorityByUsername(String username);
    public Worker selectWorkerByUsername(String username);

    public void updateIsOnline(String username);

    public void updatePassword(String password, String username);
    //工作人员离线
    public void updateIsOffline(String username);
    //检测登录状态
    public String workerIsOnline(String loginUsername);
    //为咨询师绑定督导
    List<Bind> findAllCounselor();
    List<Bind> findAllSupervisor();
    public int insertBind(String counselorName, String counselorUsername, String supervisorName, String supervisorUsername);

    //咨询师现在正在进行的求助数
    int assistCount(String username);

}
