package com.chuang.anarres.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author ath
 * @since 2021-02-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_domain_fetch_log")
public class DomainFetchLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String domain;

    private Integer times;

    private LocalDate logDate;

    private Boolean internal;
//    ALTER TABLE `seo`.`t_domain_fetch_log`
//    ADD COLUMN `internal` bit(1) NOT NULL DEFAULT 1 AFTER `log_date`;


}
