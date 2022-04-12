package com.generator.input.domains;

import lombok.Data;

/**
 * @program: code-generator
 * @time: 2022-04-08
 * @description:
 * @author: ytq
 */
@Data
public class InputFieldInfo {
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 字段类型
     */
    private String fieldType;
    /**
     * 字段注释
     */
    private String fieldComment;
    /**
     * 包访问权限
     */
    private String packageAccessor;
    /**
     * 是否生成getter和setter
     */
    private boolean generateGetAndSet;
}
