package com.example.shopping.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "作者实体类，描述图书的作者信息")
public class Author implements Serializable {
    @Schema(description = "作者ID，唯一标识")
    private Integer id;

    @NotBlank(message = "作者名不能为空")
    @Schema(description = "作者名称，图书的作者名", example = "鲁迅")
    private String name;
}
