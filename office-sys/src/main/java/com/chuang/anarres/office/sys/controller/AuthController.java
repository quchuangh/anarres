package com.chuang.anarres.office.sys.controller;

import com.chuang.anarres.office.sys.OfficeUtils;
import com.chuang.anarres.office.sys.model.bo.TreeMenuBO;
import com.chuang.anarres.office.sys.model.vo.UsersVO;
import com.chuang.anarres.office.sys.service.IMenuService;
import com.chuang.anarres.office.sys.model.vo.ShiroUser;
import com.chuang.anarres.office.sys.service.IAbilityService;
import com.chuang.anarres.office.sys.service.IRoleService;
import com.chuang.tauceti.shiro.spring.web.jwt.JwtPayload;
import com.chuang.tauceti.support.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/sys/auth")
public class AuthController {

    @Resource private IMenuService menuService;
    @Resource private IRoleService roleService;
    @Resource private IAbilityService permissionService;

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


//        List<String> roles = roleService.findByUsername(shiroUser.getUsername())
//                .stream()
//                .map(role -> ACL_ROLE_PREFIX + role.getCode())
//                .collect(Collectors.toList());
//        List<String> abilities = permissionService.findByUsername(shiroUser.getUsername())
//                .stream()
//                .map(permission -> permission.getPermission())
//                .collect(Collectors.toList());
//        vo.setRoles(roles);
//        vo.setAbilities(abilities);

        return Result.success(vo);
    }

}
