package com.chuang.anarres.configuration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Import({CoreConfiguration.class, ShiroConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.chuang.anarres")
public class SpringMvcConfiguration  implements WebMvcConfigurer {


}
