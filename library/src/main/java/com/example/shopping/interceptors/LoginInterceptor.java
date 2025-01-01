package com.example.shopping.interceptors;

import com.example.shopping.pojo.User;
import com.example.shopping.util.JwtUtil;
import com.example.shopping.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        // 不是controller的方法不拦截
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        String token = request.getHeader("Authorization"); // 从 http 请求头中取出 token
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 根据token取出保存的用户id和角色，如果为空或无效，需要重新登录
        Integer userId = JwtUtil.getIdFromToken(token);
        String userRole = JwtUtil.getRoleFromToken(token);

        if (userId == null || userRole == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 验证token，如果没有过期不做处理
        if (!JwtUtil.verifyToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 设置 ThreadLocal 中的 token
        ThreadLocalUtil.set(token);

        // 获取请求的URI
        String uri = request.getRequestURI();

        // 检查用户是否有权限访问当前 URI
        if (uri.startsWith("/admin/") && !"ADMIN".equals(userRole.toUpperCase())) {
            //用户访问管理员端返回403 代表没有权限
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        ThreadLocalUtil.remove();
    }
}
