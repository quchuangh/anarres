package com.chuang.anarres.crud.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.chuang.anarres.crud.enums.Gender;
import com.chuang.anarres.crud.enums.Language;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息 
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 昵称
     */
    @TableField("name")
    private String name;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 生日
     */
    @TableField("birthday")
    private LocalDate birthday;

    /**
     * 性别
     */
    @TableField("gender")
    private Gender gender;

    /**
     * 语言
     */
    @TableField("language")
    private Language language;

    /**
     * 是否删除
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;

    /**
     * 乐观锁
     */
    @Version
    @TableField(value = "revision", fill = FieldFill.INSERT)
    private Integer revision;

    /**
     * 创建人
     */
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private String creator;

    /**
     * 创建时间
     */
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @TableField(value = "updater", fill = FieldFill.INSERT_UPDATE)
    private String updater;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;


}
