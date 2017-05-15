package com.jflyfox.dudu.component.config;

import com.jflyfox.dudu.component.beetl.BeetlFunctions;
import com.jflyfox.dudu.component.beetl.BeetlStrUtils;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

/**
 * Created by Administrator on 2017/4/12.
 */
@Configuration
public class BeetlConfig {

    private static final Logger logger = LoggerFactory.getLogger(BeetlConfig.class);

    @Value("${beetl.templatesPath}")
    String templatesPath;//模板跟目录

    @Bean(initMethod = "init", name = "BeetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        logger.warn("####beetl init start......");

        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        try {
            ClasspathResourceLoader cploder = new ClasspathResourceLoader(BeetlConfig.class.getClassLoader(), templatesPath);
            beetlGroupUtilConfiguration.setResourceLoader(cploder);

            //读取配置文件信息
            ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
            beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("classpath:beetl.properties"));

//            Map<String, Object> functionPackages = new HashMap<String, Object>();
//            functionPackages.put("strutil", BeetlStrUtils.class);
//            beetlGroupUtilConfiguration.setFunctionPackages(functionPackages);
//            beetlGroupUtilConfiguration.setSharedVars(new HashMap<String, Object>() {{
//                put("v", 1111);
//            }});

            logger.warn("####beetl init finish.");

            return beetlGroupUtilConfiguration;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("BeetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        // beetlSpringViewResolver.setPrefix("/");
        // beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);

        // 设置方法
        beetlGroupUtilConfiguration.getGroupTemplate() //
                .registerFunctionPackage("strutil", BeetlStrUtils.class);
        beetlGroupUtilConfiguration.getGroupTemplate() //
                .registerFunctionPackage("jflyfox", BeetlFunctions.class);

        return beetlSpringViewResolver;
    }
}
