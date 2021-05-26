package com.chuang.anarres.model;

import com.baomidou.mybatisplus.annotation.*;
import com.chuang.anarres.enums.CheckedStatus;
import com.chuang.anarres.enums.TaskStatus;
import com.chuang.tauceti.support.exception.BusinessException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * subkey alt
 * </p>
 *
 * @author ath
 * @since 2020-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_seo_template")
public class SeoTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String website;

    private Integer indexSubKeySize;

    private Integer listSubKeySize;

    private Integer articleSubKeySize;

    private Integer indexATagSize;

    private Integer listATagSize;

    private Integer interlinkSize;

    private Integer articleATagSize;

    private Integer indexHSize;
    private Integer listHSize;
    private Integer articleHSize;

    private CheckedStatus checked;

    private String remark;

    private String auditor;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT)
    private String creator;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updater;

    private TaskStatus indexState;

    private TaskStatus listState;

    private TaskStatus articleState;

    private Integer groupId;

    private Integer useTimes;


    public void changeState(String pageTag, TaskStatus state) {
        if("index".equalsIgnoreCase(pageTag)) {
            this.indexState = state;
        } else if("list".equalsIgnoreCase(pageTag)) {
            this.listState = state;
        } else if("article".equalsIgnoreCase(pageTag)) {
            this.articleState = state;
        } else {
            throw new BusinessException(-1, "错误的page tag:" + pageTag);
        }
    }

    public TaskStatus state(String pageTag) {
        if("index".equalsIgnoreCase(pageTag)) {
            return this.indexState;
        } else if("list".equalsIgnoreCase(pageTag)) {
            return this.listState;
        } else if("article".equalsIgnoreCase(pageTag)) {
            return this.articleState;
        } else {
            throw new BusinessException(-1, "错误的page tag:" + pageTag);
        }
    }

}
