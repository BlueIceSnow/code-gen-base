package com.generator.common;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: code-generator
 * @time: 2022-04-08
 * @description: 类信息实体
 * @author: ytq
 */
@Data
public class ClassMetaData {
    private String classSimpleName;
    private String classFullName;
    private List<FieldMetaInfo> fieldMetaInfoList;

    public static ClassMetaData build(Class<?> clazz) {
        ClassMetaData classMetaData = new ClassMetaData();
        classMetaData.fieldMetaInfoList = new ArrayList<>();
        classMetaData.classSimpleName = clazz.getSimpleName();
        classMetaData.classFullName = clazz.getTypeName();
        Field[] declaredFields = clazz.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(item -> {
            FieldMetaInfo instance = FieldMetaInfo.instance(item);
            classMetaData.fieldMetaInfoList.add(instance);
        });
        return classMetaData;
    }
}
