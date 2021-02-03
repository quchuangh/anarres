package com.chuang.anarres.office.sys.entity;

import com.chuang.anarres.enums.RelationType;
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
 * 关系表 
 * </p>
 *
 * @author chuang
 * @since 2020-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_relation")
public class Relation implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型
     */
    @TableField("type")
    private RelationType type;

    /**
     * 祖先id
     */
    @TableField("ancestor")
    private Integer ancestor;

    /**
     * 后代id
     */
    @TableField("descendant")
    private Integer descendant;

    /**
     * 间隔代数
     */
    @TableField("distance")
    private Integer distance;

    /**
     * 附带条件_1
     */
    @TableField("condition_1")
    private String condition1;

    /**
     * 附带条件_2
     */
    @TableField("condition_2")
    private String condition2;


}
