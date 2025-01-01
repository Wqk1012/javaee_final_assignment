package com.example.shopping.controller.admin;

import com.example.shopping.mapper.CategoryMapper;
import com.example.shopping.pojo.Result;
import com.example.shopping.pojo.User;
import com.example.shopping.service.BookService;
import com.example.shopping.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Tag(name = "管理员接口", description = "提供管理员相关操作的API")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @Operation(summary = "删除用户")
    @DeleteMapping("/deleteUser")
    public Result deleteUser(Integer userId){
        User findUser = userService.findUserById(userId);
        if (findUser == null){
            return Result.error("用户不存在");
        }
        Integer count = userService.deleteUser(userId);
        if (count<1){
            return Result.error("删除失败，请重试");
        }
        return Result.success("成功删除用户"+findUser.getUsername());
    }

    @Operation(summary = "获取全部用户")
    @GetMapping("/allUser")
    public Result findAllUsers(){
        return Result.success(userService.findAllUsers());
    }


}
