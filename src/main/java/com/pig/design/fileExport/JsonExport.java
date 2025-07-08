package com.pig.design.fileExport;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.pig.design.bean.User;
import com.pig.design.mapper.UserMapper;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class JsonExport extends AbstractFileExport{



    @Override
    public FileType supportedFileType(){
        return FileType.JSON;
    }

    protected JsonExport(UserMapper userMapper){
        super(userMapper);
    }

    @Override
    protected List<User> readFile(String filepath){
        TypeReference<List<User>> userListType = new TypeReference<List<User>>() {
        };

        String json = FileUtil.readUtf8String(filepath);

        return JSONUtil.toBean(json, userListType, false);
    }

    @Override
    protected boolean needProcessData() {
        return true;
    }

    protected List<User> processData(List<User> users) {
        return users.stream()
                .map(this::processData)
                .collect(Collectors.toList());
    }

    private User processData(User user) {
        String usernameTrimmed = user.getUsername().trim();
        String nameTrimmed = user.getName().trim();

        return new User(null, usernameTrimmed, nameTrimmed);
    }
}
