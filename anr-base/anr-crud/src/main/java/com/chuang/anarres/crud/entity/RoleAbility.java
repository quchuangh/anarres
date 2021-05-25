package com.chuang.anarres.crud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色权限表;
 * </p>
 *
 * @author chuang
 * @since 2021-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_ability")
public class RoleAbility implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色编号
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 权限编号
     */
    @TableField("ability_id")
    private Integer abilityId;

    /**
     * 类型(0.仅访问,1.可授权)
     */
    @TableField("licensable")
    private Boolean licensable;


}
