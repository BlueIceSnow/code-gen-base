package com.generator.input.domains;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @program: code-generator
 * @time: 2022-04-08
 * @description:
 * @author: ytq
 */
@Data
public class InputClassInfo {
    private String name;
    private List<InputFieldInfo> fields;
    private Set<String> needImportClass;
    private String parentClass;
    private List<String> interfaces;
}
