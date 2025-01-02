package com.example.shopping.controller.user;


import com.example.shopping.mapper.UserMapper;
import com.example.shopping.pojo.Result;
import com.example.shopping.pojo.User;
import com.example.shopping.service.MinioService;
import com.example.shopping.service.UserService;
import com.example.shopping.util.FileUploadUtil;
import com.example.shopping.util.JwtUtil;
import com.example.shopping.util.PasswordUtil;
import com.example.shopping.util.ThreadLocalUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user/user")
@Tag(name = "用户管理", description = "提供用户管理相关操作的API")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private MinioService minioService;

    @Operation(summary = "注册")
    @PostMapping("/register")
    public Result register(String userName,String passWord,String email,String emailCode){
        String code = redisTemplate.opsForValue().get("emailcode");
        if (!code.equals(emailCode)){
            return Result.error("邮箱验证码不正确");
        }
        User findUser = userService.findUserByUserName(userName);
        if (findUser != null){
            return Result.error("用户名已存在");
        }
        User registerUser = new User();
        registerUser.setRole("USER");
        registerUser.setEmail(email);
        registerUser.setUsername(userName);
        registerUser.setPassword(PasswordUtil.encryptPassword(passWord));
        Integer num = userService.addUser(registerUser);
        if (num<1){
            return Result.error("服务器错误，联系管理员");
        }
        //注册后销毁验证码
        redisTemplate.delete("emailcode");
        return Result.success("注册成功");
    }


    @Operation(summary = "用户登录")
    @Parameter(name = "username", description = "用户名")
    @Parameter(name = "password", description = "密码")
    @Parameter(name = "code", description = "验证码")
    @PostMapping("/login")
    public Result login( @NotBlank(message = "用户名不能为空！") @Size(min = 4,max = 20,message = "用户名长度为4~20个字符") String username,
                            @NotBlank(message = "密码不能为空！") String password,
                            @NotBlank(message = "验证码为能为空！") String code)  {
        String captcha = redisTemplate.opsForValue().get("captcha");
        if (!captcha.equals(code)){
            return Result.error("验证码不正确");
        }
        User userByUserName = userService.findUserByUserName(username);
        if (userByUserName==null){
            return Result.error("用户不存在");
        }
        if (!PasswordUtil.verifyPassword(password,userByUserName.getPassword())){
            return Result.error("密码错误");
        }
        String s = JwtUtil.generateToken(userByUserName);
        redisTemplate.opsForValue().set(s,s,1, TimeUnit.DAYS);
        System.out.println(redisTemplate.opsForValue().get(s));
        //登录后销毁验证码
        redisTemplate.delete("captcha");
        return Result.success(s);
    }
    @PostMapping("/upload")
    @Operation(
            summary = "上传文件更新头像",
            description = "通过此接口上传文件",
            requestBody = @RequestBody(
                    description = "文件上传",
                    content = @Content(
                            mediaType = "multipart/form-data",
                            schema = @Schema(type = "object", format = "binary")
                    )
            )
    )
    public Result uploadHeadImg(@RequestParam(value = "file",required = false) MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("上传文件不能为空！");
        }

        try {
            // 使用工具类上传文件，返回文件存储路径
            String filePath = minioService.uploadFile(file);

            // 从 ThreadLocal 中获取当前用户的 Token
            String token = ThreadLocalUtil.get();

            // 从 Token 中提取用户 ID
            Integer userId = JwtUtil.getIdFromToken(token);
            if (userId == null) {
                return Result.error("用户身份校验失败，请重新登录！");
            }

            // 创建用户对象并更新头像路径
            User updateUser = new User();
            updateUser.setId(userId);
            updateUser.setAvatarPath(filePath);

            // 调用 Service 层更新用户信息
            Integer updateCount = userService.updateUser(updateUser);

            // 检查更新结果
            if (updateCount < 1) {
                return Result.error("更新用户头像失败，请稍后再试！");
            }

            // 返回成功结果
            return Result.success("上传成功，文件路径是：" + filePath);
        } catch (IOException e) {
            // 捕获文件处理异常
            return Result.error("文件上传失败，错误信息：" + e.getMessage());
        } catch (Exception e) {
            // 捕获其他异常
            return Result.error("服务器内部错误，请联系管理员！");
        }
    }

    @Operation(summary = "修改密码")
    @PutMapping("/updatepassword")
    public Result updatePassword(String newPassword,String oldPassword){
        if (newPassword.equals(oldPassword)){
            return Result.error("新旧密码不能一样");
        }
        if (newPassword.isEmpty() || oldPassword.isEmpty()){
            return Result.error("密码不能为空");
        }
        //通过JWT获取用户ID
        String token = ThreadLocalUtil.get();
        Integer userId = JwtUtil.getIdFromToken(token);
        User findUser = userService.findUserById(userId);
        if (!PasswordUtil.verifyPassword(oldPassword,findUser.getPassword())){
            return Result.error("密码错误");
        }
        findUser.setPassword(PasswordUtil.encryptPassword(newPassword));
        Integer count = userService.updateUser(findUser);
        if (count<1){
            return Result.error("修改密码失败");
        }
        return Result.success("修改密码成功");
    }
}
