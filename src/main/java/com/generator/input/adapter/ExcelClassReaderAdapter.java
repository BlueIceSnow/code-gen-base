package com.generator.input.adapter;

import com.generator.input.domains.InputClassInfo;

import java.util.List;

/**
 * @program: code-generator
 * @time: 2022-04-08
 * @description:
 * @author: ytq
 */
public class ExcelClassReaderAdapter implements IClassReaderAdapter {


    @Override
    public List<InputClassInfo> readClassInfo(String path) {

        return null;
    }

    @Override
    public boolean support(String path) {
        return path.startsWith("file://") && path.endsWith(".xlsx");
    }
}
