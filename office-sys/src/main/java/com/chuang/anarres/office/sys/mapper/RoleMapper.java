package com.chuang.anarres.office.sys.mapper;

import com.chuang.anarres.office.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色表 角色 Mapper 接口
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT * FROM sys_role r RIGHT JOIN sys_user_role ur on r.code=ur.role_code WHERE ur.username=#{username}")
    List<Role> findByUsername(@Param("username") String username);
}
