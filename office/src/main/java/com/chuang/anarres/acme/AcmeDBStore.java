package com.chuang.anarres.acme;

import com.chuang.tauceti.acme.v2.Account;
import com.chuang.tauceti.acme.v2.connection.Connection;
import com.chuang.tauceti.acme.v2.exception.AcmeStoreException;
import com.chuang.tauceti.acme.v2.provider.AcmeProvider;
import com.chuang.tauceti.acme.v2.store.AcmeStore;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Optional;

public class AcmeDBStore implements AcmeStore {
    @Override
    public void setProvider(AcmeProvider provider) {}

    @Override
    public void save(Account account) throws AcmeStoreException {

    }

    @Override
    public Optional<Account> loadAccount(String keyRef, Connection connection) {
        return Optional.empty();
    }

    @Override
    public Optional<KeyPair> loadOrderKeyPair(String orderRef) {
        return Optional.empty();
    }

    @Override
    public void saveOrderKeyPair(String orderRef, KeyPair keyPair) throws AcmeStoreException {

    }

    @Override
    public void saveOrderCsr(String orderRef, byte[] csr) throws AcmeStoreException {

    }

    @Override
    public Optional<byte[]> loadOrderCsr(String orderRef) {
        return Optional.empty();
    }

    @Override
    public void saveOrderCertificate(String orderRef, Collection<X509Certificate> x509Certificates) throws AcmeStoreException {

    }

    @Override
    public Optional<String> loadOrderLocation(String orderRef) {
        return Optional.empty();
    }

    @Override
    public void saveOrderLocation(String orderRef, String orderLocation) {

    }
}
