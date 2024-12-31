package com.example.shopping.mapper;

import com.example.shopping.pojo.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {
    // 查询特定图书的预约记录(多表)
    List<Reservation> findReservationsByBookId(@Param("bookId") Integer bookId);
    //查询用户的所有预约记录(多表)
    List<Reservation> findReservationsByUserId(@Param("userId") Integer userId);
    //查询所有图书预约记录(多表)
    List<Reservation> findAllReservations();
    // 增加预约记录
    int addReservation(Reservation reservation);
}
