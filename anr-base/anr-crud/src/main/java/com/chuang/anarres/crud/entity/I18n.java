package com.chuang.anarres.crud.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.chuang.anarres.crud.enums.I18nType;
import com.chuang.anarres.crud.enums.Language;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 国际化 
 * </p>
 *
 * @author chuang
 * @since 2020-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_i18n")
public class I18n implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * i18n
     */
    @TableField("i18n")
    private String i18n;

    /**
     * 消息
     */
    @TableField("message")
    private String message;

    /**
     * 语言
     */
    @TableField("language")
    private Language language;

    /**
     * 类型分组
     */
    @TableField("type_group")
    private I18nType typeGroup;

    @TableField("md5")
    private String md5;
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
