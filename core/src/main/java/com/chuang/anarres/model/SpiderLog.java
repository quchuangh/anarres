package com.chuang.anarres.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author ath
 * @since 2020-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_spider_log")
public class SpiderLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 百度蜘蛛个数
     */
    private Integer baidu;

    /**
     * 360蜘蛛
     */
    private Integer haosou;

    /**
     * 搜狗
     */
    private Integer sogou;

    /**
     * 易搜
     */
    private Integer yisou;

    /**
     * 其他
     */
    private Integer other;

    /**
     * 日期
     */
    private LocalDate logDate;

    /**
     * 最后更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;


}
