package com.chuang.anarres.acme;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "acme")
public class AcmeProperties {

    private String workspace;

    private String providerUri;

    private long waiting;
    private int retry;

}
