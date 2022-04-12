package com.generator;

import com.generator.common.ClassMetaData;
import com.generator.common.GeneratorConfig;
import com.generator.input.adapter.ExcelClassReaderAdapter;
import com.generator.input.adapter.IClassReaderAdapter;
import com.generator.input.adapter.TextClassReaderAdapter;
import com.generator.input.domains.InputClassInfo;
import com.generator.out.class_gen.IClassGen;
import com.generator.out.class_gen.JdkClassGen;
import com.generator.out.template_gen.ITemplateGen;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

/**
 * @program: code-generator
 * @time: 2022-04-08
 * @description:
 * @author: ytq
 */
@Data
public class Application {
    private List<ClassMetaData> classMetaDataList;
    private GeneratorConfig generatorConfig;
    private List<Class<?>> classes = new ArrayList<>();
    private List<IClassReaderAdapter> adapters = new ArrayList<>();
    private IClassGen classGen;


    private Application() {

    }

    public static ApplicationBuilder builder() {
        return new ApplicationBuilder();
    }

    /**
     * 启动应用
     */
    public Application start() {
        init();
        return this;
    }

    public void process(String path, ITemplateGen templateGen) throws Exception {
        // 获取字节码生成器
        classGen = ServiceLoader.load(IClassGen.class).findFirst()
                .orElse(new JdkClassGen());
        classGen.registryTypeMappers(generatorConfig.getTypeMappers());
        classGen.registerTemplateEngine(templateGen);
        if (classes.size() == 0) {
            // 找到合适的适配器
            final IClassReaderAdapter handlerAdapter = this.getHandlerAdapter(path);
            // 读取类配置信息
            final List<InputClassInfo> inputClassInfos = handlerAdapter.readClassInfo(path);
            // 生成类对象字节码集合
            inputClassInfos.forEach(inputClassInfo ->
                    classes.add(classGen.generatorClass(inputClassInfo, generatorConfig)));
        }
        // 生成类元数据
        classMetaDataList =
                classes.stream().map(ClassMetaData::build)
                        .collect(Collectors.toList());
        // 生成代码
        classMetaDataList
                .forEach(item -> templateGen.renderTemplateToFile(item.getClassSimpleName(),
                        generatorConfig.getOutTemplateLocation(), item,
                        generatorConfig));
    }

    public void init() {
        registerAdapter();
    }

    public IClassReaderAdapter getHandlerAdapter(String path) throws Exception {
        for (final IClassReaderAdapter adapter : adapters) {
            if (adapter.support(path)) {
                return adapter;
            }
        }
        throw new Exception("not found handler adapter");
    }

    /**
     * 注册适配器
     */
    private void registerAdapter() {
        adapters.add(new ExcelClassReaderAdapter());
        adapters.add(new TextClassReaderAdapter());
    }

    static class ApplicationBuilder {
        private final Application application;

        private ApplicationBuilder() {
            application = new Application();
        }

        public ApplicationBuilder withGeneratorConfig(GeneratorConfig generatorConfig) {
            application.generatorConfig = generatorConfig;
            return this;
        }


        public ApplicationBuilder withClasses(List<Class<?>> classes) {
            application.classes = classes;
            return this;
        }

        public Application build() {
            return application;
        }
    }
}
