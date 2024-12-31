package com.example.shopping.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.shopping.pojo.User;
import io.lettuce.core.dynamic.annotation.Value;
import lombok.Data;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Logger;


public class JwtUtil {
    // 密钥
    private static final String SECRET = "wqk2303840221";
    // 过期时间
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    /**
     * 生成 JWT Token
     *
     * @param user 用户对象
     * @return 生成的 Token
     */
    public static String generateToken(User user) {
        return JWT.create()
                .withSubject("User Token")
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("role", user.getRole())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 验证 Token
     *
     * @param token 待验证的 Token
     * @return 验证是否成功
     */
    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            // 验证失败
            return false;
        }
    }

    /**
     * 从 Token 中获取用户名
     *
     * @param token 待解析的 Token
     * @return 用户名
     */
    public static String getUsernameFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            // 解析失败
            return null;
        }
    }
    /**
     * 从 Token 中获取用户角色
     *
     * @param token 待解析的 Token
     * @return 用户角色
     */
    public static String getRoleFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("role").asString();
        } catch (JWTDecodeException e) {
            // 解析失败
            return null;
        }
    }
    /**
     * 从 Token 中获取用户 ID
     *
     * @param token 待解析的 Token
     * @return 用户 ID
     */
    public static Integer getIdFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("id").asInt();
        } catch (JWTDecodeException e) {
            // 解析失败
            return null;
        }
    }

}
