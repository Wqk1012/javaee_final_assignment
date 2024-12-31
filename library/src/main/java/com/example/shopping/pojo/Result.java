package com.example.shopping.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "统一响应类 状态码1标识失败 0成功")
public class Result<T> implements Serializable{
    @Schema(description = "业务状态码", example = "0-成功  1-失败")
    private Integer code;//业务状态码  0-成功  1-失败
    @Schema(description = "提示信息", example = "操作成功")
    private String message;//提示信息
    @Schema(description = "响应数据", example = "user对象")
    private T data;//响应数据

    //快速返回操作成功响应结果(带响应数据)
    public static <E> Result<E> success(E data) {
        return new Result<>(200, "操作成功", data);
    }

    //快速返回操作成功响应结果
    public static Result success() {
        return new Result(200, "操作成功", null);
    }

    public static Result error(String message) {
        return new Result(1, message, null);
    }
}

