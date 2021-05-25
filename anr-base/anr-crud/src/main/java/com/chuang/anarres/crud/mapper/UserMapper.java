package com.chuang.anarres.crud.mapper;

import com.chuang.anarres.crud.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import java.util.List;


/**
 * <p>
 * 用户  Mapper 接口
 * </p>
 *
 * @author chuang
 * @since 2021-05-21
 */

public interface UserMapper extends BaseMapper<User> {

    @Delete("delete from sys_user_role where username=#{username}")
    int deleteAllRole(String username);

    @Insert("<script> " +
            "insert into sys_user_role(username, role_id) " +
            "select #{username} as username, r.id as role_id  " +
            "from sys_role r " +
            "where r.role_type = #{roleType} " +
            "AND r.role in ( " +
            "<foreach collection=\"roles\" item=\"o\" open=\"\" separator=\",\">" +
            "#{o}" +
            "</foreach>" +
            " ) " +
            "</script>")
    int assignRole(String username, int roleType, List<String> roles);

    @Delete("delete from sys_user_position where username=#{username}")
    int deleteAllPosition(String username);

    @Insert("<script> " +
            "insert into sys_user_position(username, position_id) " +
            "select #{username} as username, p.id as position_id  " +
            "from sys_position p " +
            "where p.position_code in ( " +
            "<foreach collection=\"positionCodes\" item=\"o\" open=\"\" separator=\",\">" +
            "#{o}" +
            "</foreach>" +
            " ) " +
            "</script>")
    int appointment(String username, List<String> positionCodes);

    @Delete("delete from sys_user_organization where username=#{username}")
    int deleteAllOrganization(String username);

    @Insert("<script> " +
            "insert into sys_user_organization(username, organization_id) " +
            "select #{username} as username, o.id as organization_id  " +
            "from sys_organization o " +
            "where o.code in ( " +
            "<foreach collection=\"organizationCodes\" item=\"o\" open=\"\" separator=\",\">" +
            "#{o}" +
            "</foreach>" +
            " ) " +
            "</script>")
    int joinGroup(String username, List<String> organizationCodes);
}
