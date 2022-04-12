package com.generator.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: code-generator
 * @time: 2022-04-08
 * @description:
 * @author: ytq
 */
@Data
public class GeneratorConfig {
    /**
     * 首字母是否大写
     */
    private Boolean isFirstUpCase;

    /**
     * 生成类所在包前缀
     */
    private String packagePrefix;

    /**
     * 实体类包前缀
     */
    private String domainSuffix;

    /**
     * 类生成路径
     */
    private String classesOutPath = "/Users/yuantianqi/Documents/code/personal/IdeaProjects" +
            "/code_gen_base/";

    /**
     * 模板输出路径
     */
    private String templateOutPath = "/Users/yuantianqi/Documents/code/personal/IdeaProjects" +
            "/code_gen_base/";

    /**
     * 类型映射
     */
    private List<TypeMapper> typeMappers = new ArrayList<>() {{
        add(new TypeMapper("String", String.class, MatchType.EQUAL));
        add(new TypeMapper("Integer", Integer.class, MatchType.EQUAL));
        add(new TypeMapper("Float", Float.class, MatchType.EQUAL));
        add(new TypeMapper("Double", Double.class, MatchType.EQUAL));
    }};

    /**
     * 类模板路径
     */
    private String classTemplateLocation = "classpath:/templates/class-tpl.tpl";

    /**
     * 输出结果路径
     */
    private String outTemplateLocation = "classpath:/templates/out-tpl.tpl";
}
