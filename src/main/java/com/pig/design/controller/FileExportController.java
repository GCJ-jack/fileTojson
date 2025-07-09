package com.pig.design.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileExportController {

    private static final String DIR = "/Users/guochaojun/Desktop/实习文件/设计模式六合一/boot-design/data";

    @SneakyThrows
    @PostMapping("/upload")
    public String uploadAndExport(MultipartFile file) {
        //save file
        if(Objects.isNull(file)||file.isEmpty()){
            throw new IllegalArgumentException("文件为空");
        }

        File saveFile = toSavePath(file.getOriginalFilename()).toFile();

        file.transferTo(saveFile);

        return saveFile.getAbsolutePath();
    }

    private Path toSavePath(String originalFilename){
        String filename = IdUtil.fastSimpleUUID();

        String extName = FileUtil.extName(originalFilename);
        String saveFilename = filename = StrUtil.DOT + extName;

        return Paths.get(DIR, saveFilename);
    }
}
