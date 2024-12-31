package com.example.shopping.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Schema(description = "用户实体类，描述系统中的用户信息，包括管理员和普通用户")
public class User implements Serializable {
    @Schema(description = "用户ID，唯一标识")
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名，用户登录使用的名称", example = "张三")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码，用户登录使用的密码", example = "user123")
    private String password;

    @Schema(description = "头像路径，存储用户头像的文件路径", example = "/avatars/zhangsan.jpg")
    private String avatarPath;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @Schema(description = "邮箱地址，用户的联系邮箱，必须唯一", example = "zhangsan@example.com")
    private String email;

    @NotBlank(message = "角色不能为空")
    @Schema(description = "角色，标识用户的权限类型", example = "USER")
    private String role;

}
