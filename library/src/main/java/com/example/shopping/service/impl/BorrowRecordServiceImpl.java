package com.example.shopping.service.impl;

import com.example.shopping.mapper.BorrowRecordMapper;
import com.example.shopping.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {
    @Autowired
    private BorrowRecordMapper borrowRecordMapper;
}
