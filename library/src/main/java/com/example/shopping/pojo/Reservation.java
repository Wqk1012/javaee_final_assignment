package com.example.shopping.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Schema(description = "预约记录实体类，描述用户的预约图书记录")
public class Reservation implements Serializable {
    @Schema(description = "预约记录ID，唯一标识")
    private Integer id;

    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID，预约图书的用户唯一标识", example = "2")
    private Integer userId;

    @Schema(description = "用户名", example = "张三")
    private String userName;

    @NotNull(message = "图书ID不能为空")
    @Schema(description = "图书对象")
    private Integer bookId;

    @Schema(description = "图书名",example = "《活着》")
    private String bookName;


    @Schema(description = "预约日期，记录预约的时间", example = "2024-01-11")
    private LocalDate reservationDate;
}
