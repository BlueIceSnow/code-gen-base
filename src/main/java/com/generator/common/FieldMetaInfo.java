package com.generator.common;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 字段信息
 *
 * @program: code-generator
 * @time: 2022-04-08
 * @description:
 * @author: ytq
 */
@Data
public class FieldMetaInfo {
    /**
     * 字段引用
     */
    private Field field;
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 首字母大写的名称
     */
    private String firstUpCaseFieldName;
    /**
     * 是否是集合类型
     */
    private boolean isCollection;
    /**
     * 当为集合类型时，元素类型字节码
     */
    private Class<?> collectionEleType;
    /**
     * 是否为私有字段
     */
    private boolean isPrivate;
    /**
     * 属性的Getter方法
     */
    private String getterMethodName;
    /**
     * 属行的Setter方法
     */
    private String setterMethodName;

    public static FieldMetaInfo instance(Field field) {
        FieldMetaInfo fieldMetaInfo = new FieldMetaInfo();
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        fieldMetaInfo.setField(field);
        fieldMetaInfo.setPrivate(!field.isAccessible());
        fieldMetaInfo.setFieldName(field.getName());
        fieldMetaInfo.setFirstUpCaseFieldName(StringUtils.capitalize(field.getName()));
        fieldMetaInfo.setCollection(field.getGenericType().equals(List.class));
        if (fieldMetaInfo.isCollection()) {
            ParameterizedType typeParameters = (ParameterizedType) field.getGenericType();
            if (typeParameters.getActualTypeArguments().length != 0) {
                fieldMetaInfo.setCollectionEleType((Class<?>) typeParameters.getActualTypeArguments()[0]);
            }
        }
        fieldMetaInfo.setGetterMethodName("get" + fieldMetaInfo.getFirstUpCaseFieldName());
        fieldMetaInfo.setSetterMethodName("set" + fieldMetaInfo.getFirstUpCaseFieldName());
        return fieldMetaInfo;
    }
}
