package com.example.demo.service;

import com.example.demo.dao.RecordDao;
import com.example.demo.domain.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@SuppressWarnings("all")
@Service
public class RecordService {
    @Autowired
    private RecordDao recordDao;

    public List<Record> findAllBindRecord(int pageNum, int pageSize, List<String> list){
        List<Record> records = recordDao.findAllRecordBindByUsername(pageNum, pageSize, list);
        return records;
    }
}
