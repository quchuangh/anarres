package com.chuang.anarres.office.sys.controller;

import com.chuang.anarres.office.sys.model.co.UserCO;
import com.chuang.anarres.office.sys.model.ro.UserRO;
import com.chuang.anarres.office.sys.model.uo.UserUO;
import com.chuang.anarres.office.sys.entity.User;
import com.chuang.anarres.office.sys.service.IUserService;
import com.chuang.anarres.office.sys.controller.basic.ICrudController;
import javax.annotation.Resource;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户  前端控制器
 * </p>
 *
 * @author chuang
 * @since 2021-05-21
 */
@RestController
@RequestMapping("/user")
public class UserController implements ICrudController<UserCO, UserRO, UserUO, User, IUserService> {

    @Resource private IUserService service;

    @Override
    public IUserService service() {
        return service;
    }

    @Override
    public String basePermission() {
        return "user:";
    }

}

