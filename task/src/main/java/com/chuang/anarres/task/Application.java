package com.chuang.anarres.task;

import com.chuang.anarres.configuration.CoreConfiguration;
import com.chuang.tauceti.spring.boots.task.EnableAsyncTaskPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(CoreConfiguration.class)
@SpringBootApplication
@EnableAsyncTaskPool
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
