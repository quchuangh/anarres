package com.chuang.anarres.rest;

import com.chuang.anarres.configuration.CoreConfiguration;
import com.chuang.anarres.web.starter.AnrWebConfigurer;
import com.chuang.anarres.web.starter.OperatorManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.Optional;

@Import(CoreConfiguration.class)
@SpringBootApplication(scanBasePackages = "com.chuang.anarres")
public class Application implements AnrWebConfigurer {
    @Override
    public OperatorManager operatorManager() {
        return OperatorManager.create(Optional::empty);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
