package com.pig.design.fileExport;

import cn.hutool.core.io.FileTypeUtil;

import java.io.File;

public class FileExportClient {


    // 方法1：接收文件路径，自动识别文件类型后导出数据
    public static void export2Db(String filePath){
        String type = FileTypeUtil.getType(new File(filePath));
        export2Db(filePath,type);
    }
    // 方法2：接收 File 对象，获取路径后调用方法1
    public static void export2Db(File file){
        String path = file.getAbsolutePath();
        export2Db(path);
    }

    // 方法3：接收文件路径和字符串类型，根据类型从工厂获取对应处理器，执行导出
    public static void export2Db(String filePath,String type){
        FileExport fileExport = FileExportFactory.getFileExport(type);
        fileExport.export2Db(filePath);
    }
    // 方法4：接收文件路径和 FileType 枚举类型，从工厂获取对应处理器，执行导出
    public static void export2Db(String filePath, FileType fileType){
        FileExport fileExport = FileExportFactory.getFileExport(fileType);
        fileExport.export2Db(filePath);
    }
}
