package com.chuang.anarres.office.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LicensableAbility extends Ability {

    @TableField(value = "licensable")
    private Boolean licensable;
}
