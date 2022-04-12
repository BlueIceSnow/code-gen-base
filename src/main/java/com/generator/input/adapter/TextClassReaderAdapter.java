package com.generator.input.adapter;

import com.generator.input.domains.InputClassInfo;
import com.generator.input.domains.InputFieldInfo;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Program: code-generator
 * @Author: ytq
 * @Date: 2022/04/08 17:00:45
 */
public class TextClassReaderAdapter implements IClassReaderAdapter {
    @SneakyThrows
    @Override
    public List<InputClassInfo> readClassInfo(final String path) {
        FileInputStream inputStream = new FileInputStream(path);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        List<InputClassInfo> result = new ArrayList();
        String field;
        InputClassInfo current = null;
        while ((field = bufferedReader.readLine()) != null) {
            if (field.startsWith("class")) {
                current = new InputClassInfo();
                current.setName(field.split(" ")[1]);
                current.setParentClass(field.split(" ")[2]);
                current.setInterfaces(Arrays.asList(field.split(" ")[3].split(",")));
                result.add(current);
            } else if (current != null) {
                final InputFieldInfo inputFieldInfo = new InputFieldInfo();
                inputFieldInfo.setFieldName(field.split("-")[0]);
                inputFieldInfo.setFieldType(
                        field.split("-")[1].equals(" ") ? "String" : field.split("-")[1]);
                inputFieldInfo.setFieldComment(field.split("-")[2].equals(" ") ? "" :
                        field.split("-")[2]);
                inputFieldInfo.setPackageAccessor(field.split("-")[3].equals(" ") ? "private" :
                        field.split("-")[3]);
                inputFieldInfo.setGenerateGetAndSet(false);
                final List<InputFieldInfo> fields = current.getFields();
                if (fields == null) {
                    current.setFields(new ArrayList<InputFieldInfo>() {{
                        add(inputFieldInfo);
                    }});
                } else {
                    current.getFields().add(inputFieldInfo);
                }
            }
        }
        return result;
    }

    @Override
    public boolean support(final String path) {
        return path.startsWith("/") && path.endsWith(".gen");
    }
}
