package com.chuang.anarres.acme;

import com.chuang.tauceti.acme.v2.*;
import com.chuang.tauceti.acme.v2.connection.Connection;
import com.chuang.tauceti.acme.v2.store.AcmeStore;
import com.chuang.tauceti.support.exception.BusinessException;
import com.chuang.tauceti.tools.basic.FutureKit;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public class AcmeManager {

    private final Map<String, String> challengeTokenCache = new ConcurrentHashMap<>();

    @Resource
    private AcmeProperties properties;
    private final AcmeStore store;

    public AcmeManager(AcmeStore store) {
        this.store = store;
    }

    /**
     * 创建账号
     */
    public CompletableFuture<Account> createAccount(String account, String email) {
        return newConnection().loadOrNewAccount(account, false, "mailto: " + email);
    }

    public CompletableFuture<Order> apply(String account, String orderId, String domain, String... sec) {
        Account acc = newConnection().loadAccount(account)
                .orElseThrow(() -> new BusinessException(account + "找不到acme缓存，请联系管理员修复"));
        try {
            return acc.newDnsOrder(orderId, domain, null, null, sec)
                    .thenCompose(Order::updateAuthOneByOne)
                    .thenCompose(this::updateChallenge)
                    .thenCompose(this::doFinalize);
        } catch (Exception e) {
            return FutureKit.error(e);
        }
    }

    private CompletableFuture<Order> doFinalize(Order order) {
        try {
            return order.doFinalize().thenCompose(Certificate::download).thenApply(certificate -> order);
        } catch (Exception e) {
            return FutureKit.error(e);
        }
    }

    private List<Challenge> http(Order order) {
        return order.getAuthorizations().stream()
                .map(Authorization::http)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private CompletableFuture<Order> updateChallenge(Order order) {
        List<Challenge> challenges = this.http(order);

        CompletableFuture<List<Challenge>> futures = FutureKit.oneByOne(challenges, this::updateChallenge, throwable -> null);

        return futures.thenApply(c -> order);
    }

    private CompletableFuture<Challenge> updateChallenge(Challenge challenge) {
        return challenge.update(properties.getWaiting(), properties.getRetry()).thenApply(c -> {
            this.challengeTokenCache.put(c.getToken(), challenge.getAuthorization().getOrder().getAccount().thumbprint());
            return c;
        });
    }

    private Connection newConnection() {
        Session session = new Session(properties.getProviderUri(), store);
        return session.connect();
    }

    public Optional<String> checkAndRemove(String challengeToken) {
        return Optional.ofNullable(challengeTokenCache.remove(challengeToken));
    }

    public void clearToken() {
        this.challengeTokenCache.clear();
    }
}
