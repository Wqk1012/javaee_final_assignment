package com.example.shopping.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    /**
     * 加密密码
     *
     * @param plainPassword 明文密码
     * @return 加密后的密码
     */
    public static String encryptPassword(String plainPassword) {
        // 使用BCrypt生成哈希密码
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    /**
     * 验证密码
     *
     * @param plainPassword 明文密码
     * @param hashedPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        // 验证密码是否匹配
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
