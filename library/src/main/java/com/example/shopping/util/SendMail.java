package com.example.shopping.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SendMail {
    @Autowired
    private  JavaMailSender mailSender;

    public  void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("wqk2303840221@163.com"); // 发件人
        message.setTo(to); // 收件人
        message.setSubject(subject); // 邮件主题
        message.setText(content); // 邮件正文
        mailSender.send(message);
    }

    public static String generateCaptcha() {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();

        // 生成 6 位数字验证码
        for (int i = 0; i < 6; i++) {
            captcha.append(random.nextInt(10));  // 生成 0-9 的随机数字
        }

        return captcha.toString();
    }
}
