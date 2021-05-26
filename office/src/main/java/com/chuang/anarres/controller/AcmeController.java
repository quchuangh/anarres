package com.chuang.anarres.controller;


import com.chuang.anarres.acme.AcmeManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Slf4j
@RestController
public class AcmeController {

    @Resource private AcmeManager acme;

    @RequestMapping("/.well-known/acme-challenge/{token}")
    public String challenge(@PathVariable("token") String token) {
        log.info("token:===> " + token);
        return acme.checkAndRemove(token)
                .map(thumbprint -> token + "." + thumbprint)
                .orElse("token error");
    }

    @PostMapping("/acme/apply")
    public Mono<String> apply(String account, String domain) {

        acme.apply(account, domain, domain, "www.");
        return null;
    }


}
