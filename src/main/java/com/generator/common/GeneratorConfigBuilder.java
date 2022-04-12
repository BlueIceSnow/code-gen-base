package com.generator.common;

import java.util.List;

/**
 * 单例构建器，多线程构建配置.
 *
 * @program: code-generator
 * @time: 2022-04-08
 * @description:
 * @author: ytq
 */
public class GeneratorConfigBuilder {

    private static volatile GeneratorConfigBuilder builder;
    private final ThreadLocal<GeneratorConfig> configThreadLocal = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return new GeneratorConfig();
        }
    };

    private GeneratorConfigBuilder() {
    }


    // 单例双重检测
    public static GeneratorConfigBuilder builder() {
        if (builder == null) {
            synchronized (GeneratorConfigBuilder.class) {
                if (builder == null) {
                    builder = new GeneratorConfigBuilder();
                }
            }
        }
        return builder;
    }

    public GeneratorConfigBuilder withIsFirstUpCase(Boolean isFirstUpCase) {
        configThreadLocal.get().setIsFirstUpCase(isFirstUpCase);
        return this;
    }

    public GeneratorConfigBuilder withPackagePrefix(String packagePrefix) {
        configThreadLocal.get().setPackagePrefix(packagePrefix);
        return this;
    }

    public GeneratorConfigBuilder withDomainSuffix(String domainPrefix) {
        configThreadLocal.get().setDomainSuffix(domainPrefix);
        return this;
    }

    public GeneratorConfigBuilder withTypeMappers(List<TypeMapper> typeMappers) {
        configThreadLocal.get().setTypeMappers(typeMappers);
        return this;
    }

    public GeneratorConfigBuilder withClassTemplateLocation(String classTemplateLocation) {
        configThreadLocal.get().setClassTemplateLocation(classTemplateLocation);
        return this;
    }

    public GeneratorConfigBuilder withOutTemplateLocation(String outTemplateLocation) {
        configThreadLocal.get().setOutTemplateLocation(outTemplateLocation);
        return this;
    }

    public GeneratorConfig build() {
        GeneratorConfig config = configThreadLocal.get();
        configThreadLocal.remove();
        return config;
    }
}
