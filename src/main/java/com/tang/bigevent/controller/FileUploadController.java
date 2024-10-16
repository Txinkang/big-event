package com.tang.bigevent.controller;

import com.tang.bigevent.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile multipartFile) throws IOException {
        //获取原文件名
        String originalFileName= multipartFile.getOriginalFilename();
        //获取扩展名并且加上uuid
        String fileName= UUID.randomUUID().toString()+originalFileName.substring(originalFileName.lastIndexOf("."));
        //存进本地磁盘
        multipartFile.transferTo(new File("/Users/tangxinkang/Projects/IDeaProjects/big-event/src/main/resources/static/testImg/"+fileName));

        return Result.success("成功");
    }
}
