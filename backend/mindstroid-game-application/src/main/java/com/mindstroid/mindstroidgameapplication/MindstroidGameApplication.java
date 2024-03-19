package com.mindstroid.mindstroidgameapplication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Date;

@SpringBootApplication(scanBasePackages = {
        "com.mindstroid.mindstroidgameapplication"
})
public class MindstroidGameApplication extends SpringBootServletInitializer {

    private static Logger audit = LogManager.getLogger("audit-log");

    public static void main(String[] args) {

        audit.info("mindstroid application,at," + new Date() + ",start,ok");
        new SpringApplicationBuilder(MindstroidGameApplication.class)
                .properties("spring.config.name:mindstroid_main")
                .properties("spring.config.location:/etc/mindstroid/backend/")
                .build()
                .run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MindstroidGameApplication.class);
    }
}
