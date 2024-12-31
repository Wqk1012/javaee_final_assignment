package com.example.shopping.pojo;

import ch.qos.logback.core.model.INamedModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Schema(description = "借阅记录实体类，描述用户的借阅图书记录")
public class BorrowRecord implements Serializable {
    @Schema(description = "借阅记录ID，唯一标识")
    private Integer id;

    @NotNull(message = "用户ID不能为空")
    @Schema(description = "用户ID")
    private Integer userId;

    @NotNull(message = "图书ID不能为空")
    @Schema(description = "图书ID")
    private Integer bookId;

    @NotNull(message = "借阅日期不能为空")
    @Schema(description = "借阅日期，记录借阅的时间", example = "2024-01-01")
    private LocalDate borrowDate;

    @Schema(description = "归还日期，记录图书归还的时间", example = "2024-01-15")
    private LocalDate returnDate;
}
