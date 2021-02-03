package com.chuang.anarres.office.sys.model.bo;


import com.chuang.anarres.enums.PermissionType;
import lombok.Data;

@Data
public class PermissionBO {
    /**
     * 编号
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 权限字符
     */
    private String permission;

    /**
     * 描述
     */
    private String description;

    private PermissionType rightType;
}
