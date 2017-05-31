package com.jflyfox.dudu;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Administrator on 2017/4/11.
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, ThymeleafAutoConfiguration.class})
public class DuduMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.application().setBannerMode(Banner.Mode.OFF);
        return application.sources(DuduMain.class);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DuduMain.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

}
