package com.xiaomo.controller;

import com.xiaomo.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @PostMapping("/uploadFile")
    // localhost:9009/dev/file/uploadFile
    public Result uploadFile(MultipartFile file) throws IOException {
        // 获取文件的明村
        String originalFilename= file.getOriginalFilename();

        // file.getSize();// 获取文件大小，单位：字节
        // file.getBytes();// 获取文件内容的字节数组
        // file.getInputStream();// 获取文件内容的输入流

        // 保证名字唯一，所以加上uuid
        String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 将接收的文件转存到磁盘
        file.transferTo(new File("C:\\Users\\Administrator\\Desktop\\file\\" + fileName));

        return Result.success("文件url的地址...");
    }

}
