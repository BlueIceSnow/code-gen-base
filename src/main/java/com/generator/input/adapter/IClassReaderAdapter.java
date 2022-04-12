package com.generator.input.adapter;

import com.generator.input.domains.InputClassInfo;

import java.util.List;

/**
 * @program: code-generator
 * @time: 2022-04-08
 * @description:
 * @author: ytq
 */
public interface IClassReaderAdapter {

    /**
     * 读取类信息
     * @param path 读取地址
     * @return 返回读取的类信息
     */
    List<InputClassInfo> readClassInfo(String path);


    /**
     * 是否支持
     * @param path 读取路径
     * @return 是否支持
     */
    boolean support(String path);
}
