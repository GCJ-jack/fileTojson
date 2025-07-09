package com.pig.design.fileExport;

import cn.hutool.extra.spring.SpringUtil;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileExportFactory {


    // 定义一个工厂类，用于根据文件类型 FileType 返回对应的 FileExport 实现类
    // 使用静态代码块在类加载时完成一次性的初始化操作
    // 1. 定义一个静态 Map，用来缓存 FileType -> FileExport 实现的映射关系（注册表）
    private static final Map<FileType,FileExport> CACHE;

    // 2. 在静态代码块中：
    //    a. 从 Spring 容器中获取所有实现了 FileExport 接口的 Bean 实例（Map<String, FileExport>）
    //    b. 遍历这些 Bean，调用每个实现的 supportedFileType() 方法作为 key
    //    c. 将 FileType 和对应的实现类作为键值对放入缓存 Map 中

    static {
        Map<String, FileExport> beans = SpringUtil.getBeansOfType(FileExport.class);

        CACHE = beans.values().stream()
                .collect(Collectors.toConcurrentMap(
                        FileExport::supportedFileType,
                        Function.identity(),
                        (v1,v2) -> v2
                ));
    }

    // 3. 提供一个公共静态方法：getFileExport(FileType)
    //    a. 判断该类型是否存在于缓存中
    //    b. 如果不存在，抛出非法参数异常
    //    c. 如果存在，从缓存中取出并返回

    public static FileExport getFileExport(FileType fileType){
        if(!CACHE.containsKey(fileType)){
            throw new IllegalArgumentException("couldn't find the match file type " + fileType.getType());
        }

        return CACHE.get(fileType);
    }

    // 4. 提供重载方法：getFileExport(String)
    //    a. 将字符串类型转换为 FileType（使用 FileType.from 方法）
    //    b. 调用 getFileExport(FileType) 获取对应的实现类
    public static FileExport getFileExport(String type){
        FileType fileType = FileType.from(type);

        return getFileExport(fileType);
    }
}
