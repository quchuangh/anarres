package com.chuang.anarres.office.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.chuang.anarres.office.sys.enums.FieldAction;
import com.chuang.tauceti.tools.basic.StringKit;
import com.chuang.tauceti.tools.basic.collection.CollectionKit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * api字段权限
 * </p>
 *
 * @author chuang
 * @since 2021-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_api_field_acl")
public class ApiFieldAcl implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 接口编号
     */
    @TableField("api_code")
    private String apiCode;

    /**
     * 字段名
     */
    @TableField("field_el")
    private String fieldEl;

    /**
     * 字段标签
     */
    @TableField("field_label")
    private String fieldLabel;

    /**
     * 需要的角色
     */
    @TableField("roles")
    private String roles;

    /**
     * 需要的权限
     */
    @TableField("abilities")
    private String abilities;

    /**
     * 是否为入参
     */
    @TableField("income")
    private Boolean income;

    /**
     * 符合要求后的行为
     */
    @TableField("action")
    private FieldAction action;

    /**
     * 创建人
     */
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private String creator;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @TableField(value = "updater", fill = FieldFill.INSERT_UPDATE)
    private String updater;

    /**
     * 更新时间
     */
    @TableField("updated_time")
    private LocalDateTime updatedTime;


    public List<String> abilities() {
        return new ArrayList<>(Arrays.asList(this.abilities.split(",")));
    }

    public void abilities(List<String> abilities) {
        this.abilities = StringKit.join(abilities, ",");
    }

    public List<String> roles() {
        return new ArrayList<>(Arrays.asList(this.roles.split(",")));
    }

    public void roles(List<String> roles) {
        this.roles = StringKit.join(roles, ",");
    }
}
