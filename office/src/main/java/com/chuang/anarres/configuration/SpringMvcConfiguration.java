package com.chuang.anarres.configuration;

import com.chuang.anarres.acme.AcmeManager;
import com.chuang.anarres.acme.AcmeProperties;
import com.chuang.tauceti.acme.v2.store.AcmeStore;
import com.chuang.tauceti.acme.v2.store.FileStore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Import({CoreConfiguration.class, ShiroConfiguration.class})
@SpringBootApplication(scanBasePackages = "com.chuang.anarres")
public class SpringMvcConfiguration  implements WebMvcConfigurer {

    @Bean
    public AcmeManager acmeManager(AcmeStore store) {
        return new AcmeManager(store);
    }

    @Bean
    public AcmeStore acmeStore(AcmeProperties acmeProperties) {
        return new FileStore(acmeProperties.getWorkspace());
    }
}
