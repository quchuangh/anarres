package com.chuang.anarres.configuration;

import com.chuang.tauceti.spring.boots.task.EnableAsyncTaskPool;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Import({CoreConfiguration.class, ShiroConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.chuang.anarres")
@EnableAsyncTaskPool
public class SpringMvcConfiguration  implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
