package com.chuang.anarres.service.polymer;

import org.springframework.transaction.annotation.Transactional;

public interface  IWebBuildService {

    @Transactional(rollbackFor = Exception.class)
    boolean audit(Integer id, String remark, Boolean pass, String username);

    @Transactional(rollbackFor = Exception.class)
    void offline(Integer id);
}
