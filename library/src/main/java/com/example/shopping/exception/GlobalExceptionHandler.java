package com.example.shopping.exception;

import com.example.shopping.pojo.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import java.io.IOException;
import java.util.Map;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    //标识该方法会处理对应异常类及其子类
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败");
    }
    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public Map<String, Object> handleFileUploadFail(MultipartException ex, HttpServletRequest request) throws ServletException, IOException {
        log.error("File upload failed", ex);
        // 记录请求的所有部分
        request.getParts().forEach(part -> log.info("Part name: {}, size: {}", part.getName(), part.getSize()));
        return Map.of("message", "文件上传失败，请检查请求格式和文件大小限制");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public Map<String, Object> handleMaxSizeExceeded(MaxUploadSizeExceededException ex) {
        log.error("Maximum upload size exceeded", ex);
        return Map.of("message", "文件太大，超过了允许的最大尺寸");
    }
}
