package com.generator;

import com.generator.common.GeneratorConfig;
import com.generator.common.GeneratorConfigBuilder;
import com.generator.out.template_gen.ThymeleafTemplateGen;


/**
 * @Program: code-generator
 * @Author: ytq
 * @Date: 2022/04/08 17:27:47
 */
public class Main {
    public static void main(String[] args) throws Exception {
        final GeneratorConfig generatorConfig = GeneratorConfigBuilder.builder()
                .withDomainSuffix("com.tianqi")
                .withPackagePrefix("com.tianqi")
                .withIsFirstUpCase(true)
                .build();
        final Application application = Application.builder()
                .withGeneratorConfig(generatorConfig).build();
        application.start();
        application.process("/Users/yuantianqi/Documents/code/personal/IdeaProjects" +
                "/code_gen_base/src/main/resources/classes-info.gen", new ThymeleafTemplateGen());
    }
}
