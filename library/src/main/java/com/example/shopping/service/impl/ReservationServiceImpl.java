package com.example.shopping.service.impl;

import com.example.shopping.mapper.ReservationMapper;
import com.example.shopping.pojo.Reservation;
import com.example.shopping.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;

    // 查询特定图书的预约记录(多表)
    @Override
    public List<Reservation> findReservationsByBookId(Integer bookId) {
        return reservationMapper.findReservationsByBookId(bookId);
    }

    // 查询用户的所有预约记录(多表)
    @Override
    public List<Reservation> findReservationsByUserId(Integer userId) {
        return reservationMapper.findReservationsByUserId(userId);
    }

    // 查询所有图书预约记录(多表)
    @Override
    public List<Reservation> findAllReservations() {
        return reservationMapper.findAllReservations();
    }

    // 增加预约记录
    @Override
    public Integer addReservation(Reservation reservation) {
        return reservationMapper.addReservation(reservation);
    }
}
