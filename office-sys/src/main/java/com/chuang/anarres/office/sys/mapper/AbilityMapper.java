package com.chuang.anarres.office.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuang.anarres.office.sys.entity.Ability;
import com.chuang.anarres.office.sys.entity.LicensableAbility;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 权限表 权限 Mapper 接口
 * </p>
 *
 * @author chuang
 * @since 2021-01-05
 */
public interface AbilityMapper extends BaseMapper<Ability> {

    @Select("SELECT a.*, ra.licensable " +
            "FROM sys_ability a " +
            "LEFT JOIN sys_role_ability ra " +
            "ON ra.ability_id = a.id " +
            "WHERE ra.role_id = #{roleId} " +
            "AND a.enabled=true ")
    @ResultType(LicensableAbility.class)
    List<LicensableAbility> findByRoleId(@Param("roleId") Integer roleId);

    @Select("SELECT a.*, ra.licensable FROM sys_ability a " +
            "LEFT JOIN sys_role_ability  ra " +
            "ON a.id = ra.ability_id  " +
            "LEFT JOIN sys_user_role ur " +
            "ON ra.role_id = ur.role_id " +
            "WHERE ur.username = #{username} " +
            "AND a.enabled=true ")
    @ResultType(LicensableAbility.class)
    List<LicensableAbility> findByUsername(String username);
}
