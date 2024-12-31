package com.example.shopping.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "统计用户借阅书籍对象")
public class UserBorrowedBooksCount implements Serializable {
    private Integer userId;
    private String userName;
    private Integer borrowedBooksCount;
}
