package com.example.shopping.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUploadUtil {
    private static final String UPLOAD_DIR = "D:/img"; // 文件保存的根目录

    /**
     * 上传文件并返回文件完整路径
     *
     * @param file MultipartFile 对象
     * @return 文件的完整路径
     * @throws IOException 如果文件保存失败
     */
    public static String uploadFile(MultipartFile file) throws IOException {
        // 检查文件是否为空
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("文件不能为空！");
        }

        // 获取文件的原始扩展名
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // 使用 UUID 生成新的文件名
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        // 保存文件到指定路径
        File uploadFile = new File(UPLOAD_DIR, newFileName);
        if (!uploadFile.getParentFile().exists()) {
            uploadFile.getParentFile().mkdirs(); // 如果目录不存在，创建目录
        }

        file.transferTo(uploadFile);

        // 返回文件的完整路径
        return uploadFile.getAbsolutePath();
    }
}
