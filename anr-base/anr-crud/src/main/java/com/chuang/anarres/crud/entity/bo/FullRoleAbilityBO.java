package com.chuang.anarres.crud.entity.bo;

import com.chuang.anarres.crud.entity.LicensableAbility;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FullRoleAbilityBO extends LicensableAbility {

    /** 当前用户可操作 */
    private Boolean disableOpt;

    /** 已经被选择 */
    private Boolean checked;


}
