package com.chuang.anarres.office.sys.controller;

import com.chuang.anarres.office.sys.OfficeUtils;
import com.chuang.anarres.office.sys.model.bo.TreeMenuBO;
import com.chuang.anarres.office.sys.model.vo.UsersVO;
import com.chuang.anarres.office.sys.service.IMenuService;
import com.chuang.anarres.office.sys.model.vo.ShiroUser;
import com.chuang.tauceti.support.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/sys/auth")
public class AuthController {


    @Resource
    private IMenuService menuService;

    @PostMapping("/logout")
    @ApiOperation("登出当前用户")
    public Result<Void> logout() {
        Subject subject = SecurityUtils.getSubject();//当前使用的角色(不一定是登录用户)
        subject.logout();
        return Result.success();
    }


    @GetMapping("/users")
    @ApiOperation("获取用户登录后的相关资料")
    public Result<UsersVO> users() {
        ShiroUser shiroUser = OfficeUtils.shiroUserNotNull();
        List<TreeMenuBO> menus = menuService.userMenus(shiroUser.getUsername());

        UsersVO vo = new UsersVO();
        vo.setMenu(menus);
        vo.setUser(shiroUser);

        return Result.success(vo);
    }

}
