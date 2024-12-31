package com.example.shopping.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
@Schema(description = "分类实体类，描述图书的分类信息")
public class Category {
    @Schema(description = "分类ID，唯一标识")
    private Integer id;

    @NotBlank(message = "分类名不能为空")
    @Schema(description = "分类名称，图书分类的名称", example = "文学")
    private String name;
}
