package com.chuang.anarres;

import com.chuang.anarres.configuration.SpringMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;


public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringMvcConfiguration.class, args);
        System.out.println("bean: " + context.getBean(SpringMvcConfiguration.class));
    }

}
