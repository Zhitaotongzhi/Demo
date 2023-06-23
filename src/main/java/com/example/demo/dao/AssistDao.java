package com.example.demo.dao;

import com.example.demo.domain.Assist;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AssistDao {
    public int insertAssist(Assist assist);
}
