package com.example.shopping.controller.user;

import com.example.shopping.pojo.Result;
import com.example.shopping.util.SendMail;
import com.google.code.kaptcha.Producer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/code")
public class CodeController {
    @Autowired
    private SendMail sendMail;
    @Autowired
    private Producer captchaProducer; // 默认是DefaultKaptcha的实例
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Operation(summary = "获取登录验证码")
    @GetMapping(value = "captcha", produces = MediaType.IMAGE_JPEG_VALUE)
    public void captchaImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应头，不缓存图片
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 生成验证码文本和图片
        String text = captchaProducer.createText();
        // 可以将验证码文本保存到redis中，以便后续验证
        redisTemplate.opsForValue().set("captcha",text,5, TimeUnit.MINUTES);
        BufferedImage image = captchaProducer.createImage(text);
        try (ServletOutputStream out = response.getOutputStream()) {
            // 将图片写入响应输出流
            ImageIO.write(image, "jpg", out);
        }
    }
    @Operation(summary = "获取邮箱验证码")
    @Parameter(name = "mail", description = "邮箱")
    @GetMapping("/emailcode")
    public Result emailCode(String mail){
        String code = SendMail.generateCaptcha();
        sendMail.sendSimpleMail(mail,"验证码",code);
        redisTemplate.opsForValue().set("emailcode",code,5, TimeUnit.MINUTES);
        System.out.println(redisTemplate.opsForValue().get("emailcode"));
        return Result.success();
    }
}
