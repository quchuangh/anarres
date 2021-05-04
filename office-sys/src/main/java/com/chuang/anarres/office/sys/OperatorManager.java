package com.chuang.anarres.office.sys;

import com.chuang.anarres.office.sys.model.ShiroUser;
import com.chuang.urras.rowquery.handlers.DefaultValueGetter;
import com.chuang.urras.rowquery.handlers.ValueGetter;

public class OperatorManager extends DefaultValueGetter<String> implements ValueGetter<String> {

    public OperatorManager() {
        super(() -> OfficeUtils.shiroUser().map(ShiroUser::getUsername));
    }
}
