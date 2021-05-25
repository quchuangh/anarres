package com.chuang.anarres.office.sys.mapper;

import com.chuang.anarres.office.sys.entity.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 职位表; Mapper 接口
 * </p>
 *
 * @author chuang
 * @since 2021-05-24
 */
public interface PositionMapper extends BaseMapper<Position> {

    @Select("select p.* from sys_position p " +
            "left join sys_user_position up " +
            "on p.id = up.position_id " +
            "where up.username=#{username} ")
    List<Position> findByUser(String username);
}
