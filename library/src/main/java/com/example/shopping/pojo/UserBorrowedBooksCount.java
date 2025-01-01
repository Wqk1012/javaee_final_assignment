package com.example.shopping.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "统计用户借阅书籍对象")
public class UserBorrowedBooksCount implements Serializable {
    @Schema(description = "用户ID，用户唯一标识", example = "1")
    private Integer userId;
    @Schema(description = "用户名", example = "zhangsan")
    private String userName;
    @Schema(description = "借阅书籍数量", example = "2")
    private Integer borrowedBooksCount;
}
