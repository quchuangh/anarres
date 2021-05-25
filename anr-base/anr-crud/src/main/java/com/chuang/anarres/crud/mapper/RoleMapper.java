package com.chuang.anarres.crud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuang.anarres.crud.entity.Role;
import org.apache.ibatis.annotations.Select;

import javax.annotation.Nullable;
import java.util.List;

/**
 * <p>
 * 角色表;角色 Mapper 接口
 * </p>
 *
 * @author chuang
 * @since 2021-05-07
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT r.* " +
            "FROM sys_user_role ur LEFT JOIN sys_role r " +
            "ON ur.role_id = r.id " +
            "WHERE username = #{username} ")
    List<Role> findUserRoles(String username);

    @Select("SELECT r.* FROM sys_role r " +
            "LEFT JOIN sys_position p " +
            "ON r.role = p.role_code " +
            "LEFT JOIN sys_user_position up " +
            "ON p.id = up.position_id " +
            "WHERE username=#{username} ")
    @Nullable Role findPositionRole(String username);

    @Select("SELECT r.* FROM sys_role r " +
            "LEFT JOIN sys_organization o " +
            "ON r.role = o.role_code " +
            "LEFT JOIN sys_user_organization uo " +
            "ON o.id = uo.organization_id " +
            "WHERE username=#{username}")
    @Nullable Role findOrgRole(String username);
}
