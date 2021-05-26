package com.chuang.anarres.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SeoDomainTaskVO extends SeoDomain {

    private int num;
}
