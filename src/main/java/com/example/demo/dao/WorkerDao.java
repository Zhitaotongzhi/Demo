package com.example.demo.dao;

import com.example.demo.domain.Worker;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@SuppressWarnings("all")
@Mapper
@Repository
public interface WorkerDao {
    public String selectAuthorityByUsername(String username);
    public Worker selectWorkerByUsername(String username);

    public void updateIsOnline(String username);

    public void updatePassword(String password, String username);

}
