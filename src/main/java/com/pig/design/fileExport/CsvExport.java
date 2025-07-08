package com.pig.design.fileExport;

import com.pig.design.bean.User;
import com.pig.design.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CsvExport extends AbstractFileExport{

    protected CsvExport(UserMapper userMapper) {
        super(userMapper);
    }

    @Override
    public FileType supportedFileType() {
        return FileType.CSV;
    }

    @Override
    protected List<User> readFile(String filepath) {
        System.out.println("以csv方式读文件");

        return new ArrayList<>();
    }

    @Override
    protected boolean needProcessData() {
        return false;
    }
}
