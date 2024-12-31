package com.example.shopping.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "图书DTO类，用于数据库操作")
public class BookDTO implements Serializable {
    @Schema(description = "图书ID，唯一标识")
    private Integer id;

    @NotBlank(message = "图书标题不能为空")
    @Schema(description = "图书标题，图书的名称", example = "呐喊")
    private String title;

    @NotNull(message = "作者ID不能为空")
    @Schema(description = "作者ID", example = "1")
    private Integer authorId;

    @NotNull(message = "分类ID不能为空")
    @Schema(description = "分类ID", example = "2")
    private Integer categoryId;

    @NotNull(message = "出版年份不能为空")
    @Schema(description = "出版年份，图书的出版时间", example = "1923")
    private Integer publishedYear;
}
