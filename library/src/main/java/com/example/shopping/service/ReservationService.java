package com.example.shopping.service;

import com.example.shopping.pojo.Reservation;

import java.util.List;

public interface ReservationService {
    // 查询特定图书的预约记录(多表)
    List<Reservation> findReservationsByBookId(Integer bookId);

    // 查询用户的所有预约记录(多表)
    List<Reservation> findReservationsByUserId(Integer userId);

    // 查询所有图书预约记录(多表)
    List<Reservation> findAllReservations();

    // 增加预约记录
    Integer addReservation(Reservation reservation);
}
