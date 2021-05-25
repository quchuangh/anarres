package com.chuang.anarres.office.sys.service;

import com.chuang.anarres.office.sys.entity.User;
import com.chuang.urras.rowquery.IRowQueryService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 用户  服务类
 * </p>
 *
 * @author chuang
 * @since 2021-05-21
 */
public interface IUserService extends IRowQueryService<User> {

    default Optional<User> findByUsername(String username) {
        return lambdaQuery().eq(User::getUsername,username).oneOpt();
    }

    /**
     * 给用户重置角色
     * @param username 用户名
     * @param roles 角色编号 集合
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    boolean assignRole(String username, List<String> roles);

    /**
     * 重置用户加入的组织列表
     * @param username 用户名
     * @param organizationCodes 组织编号集合
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    boolean resetJoinGroup(String username, List<String> organizationCodes);

    /**
     * 重置用户职位列表
     * @param username 用户名
     * @param positionCodes 职位编号集合
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    boolean  resetAppointment(String username, List<String> positionCodes);

}
