package com.pig.design.fileExport;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@ToString
@AllArgsConstructor
public enum FileType {

    JSON("json"),
    CSV("csv");

    private final String type;

    private final static Map<String, FileType> VALUE_MAP = Arrays.stream(values())
            .collect(Collectors.toMap(
                    FileType::getType,
                    Function.identity(),
                    (v1, v2) -> v2
            ));


    public static FileType from(String type){
        if(!VALUE_MAP.containsKey(type)) {
            throw new IllegalArgumentException("找不到对应类型： " + type);
        }
        return VALUE_MAP.get(type);
    }

}
