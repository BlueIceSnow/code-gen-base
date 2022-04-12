package com.generator.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

/**
 * 类型映射
 *
 * @Program: code_gen_base
 * @Author: ytq
 * @Date: 2022/04/11 09:57:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeMapper {

    /**
     * 类型简称
     */
    private String matchStr;

    /**
     * 类型字节码
     */
    private Class<?> clazz;

    /**
     * 匹配类型
     */
    private MatchType matchType;

    /**
     * 判断是否匹配
     *
     * @param simpleClassName 简称
     * @return 是否匹配
     */
    public Boolean match(String simpleClassName) throws Exception {
        switch (matchType) {
            case REGEX:
                return Pattern.matches(this.matchStr, simpleClassName);
            case EQUAL:
                return this.matchStr.equals(simpleClassName);
            default:
                throw new Exception("not fount match Type");
        }
    }

}