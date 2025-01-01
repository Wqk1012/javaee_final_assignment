package com.example.shopping.service.impl;

import com.example.shopping.mapper.ReservationMapper;
import com.example.shopping.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;
}
