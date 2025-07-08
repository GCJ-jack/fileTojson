package com.pig.design.fileExport;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.map.multi.AbsTable;
import cn.hutool.core.util.StrUtil;
import com.mysql.cj.util.StringUtils;
import com.pig.design.bean.User;
import com.pig.design.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Objects;

public abstract class AbstractFileExport implements FileExport{

    private final UserMapper userMapper;

    @Autowired
    protected AbstractFileExport(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void export2Db(String filepath){
        //检验路径是否为空

        // 读取文件到对象 交给子类去实现

        // 可能需要再做数据处理 比如数据中有空格 去空格

        //保存到数据中
    }

    private void check(String filePath){
        if(StrUtil.isBlank(filePath)){
            throw new IllegalArgumentException("文件路径不能为空");
        }

        File file = new File(filePath);

        if(!file.exists() || file.isDirectory()){
            throw new IllegalArgumentException("文件不能为空切不能是文件夹");
        }

        String type = FileTypeUtil.getType(file);

        if(!Objects.equals(supportedFileType().getType(),type)){
            throw new IllegalArgumentException("文件类型异常：" + type);
        }
    }

    protected abstract List<User> readFile(String filepath);

    protected abstract boolean needProcessData();

    protected List<User> processData(List<User> users){
        throw new UnsupportedOperationException("不支持数据处理");
    }

    private void saveUsers(List<User> users) {
        // todo !!!真实开发中, 禁止在循环中去做数据库操作, 禁止在循环中做远程调用
        users.forEach(userMapper::insert);
    }


}
