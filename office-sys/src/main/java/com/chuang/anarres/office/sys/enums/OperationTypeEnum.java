package com.chuang.anarres.office.sys.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.swagger.annotations.ApiModel;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Optional;

@Getter
@ApiModel(value = "OperationType", description = "操作类型")
public enum OperationTypeEnum {
    /**
     * 读取
     */
    RETRIEVED(1),
    /**
     * 更新
     */
    UPDATED(2),
    /**
     * 创建
     */
    CREATED(3),
    /**
     * 删除
     */
    DELETED(4),
    /**
     * 逻辑删除
     */
    LOGIC_DELETED(5),
    /**
     * 导出
     */
    EXPORTED(6),
    /**
     * 导入
     */
    IMPORTED(7),
    /**
     * 审批
     */
    APPROVED(8),
    /**
     * 强制用户下线
     */
    OFFLINE(9),
    ;

    @EnumValue
    private final Integer code;

    OperationTypeEnum(Integer code) {
        this.code = code;
    }

    public static Optional<OperationTypeEnum> valueOf(Integer code) {
        return EnumSet
                .allOf(OperationTypeEnum.class)
                .stream()
                .filter(v -> v.code.equals(code))
                .findFirst();
    }
}
