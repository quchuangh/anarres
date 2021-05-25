package com.chuang.anarres.office.sys.service;


import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public interface IAuthService {

    @Transactional(rollbackFor = Exception.class)
    Optional<String> createUser(String username, String name, @Nullable String ipBound, @Nullable String macBound);

    boolean forceChangePassword(String username, String password);

    boolean changePassword(String username, String oldPassword, String newPassword);


}
