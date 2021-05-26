package com.chuang.anarres;

import com.chuang.tauceti.rowquery.RowQuery;
import com.chuang.tauceti.rowquery.filters.TextFilter;
import com.chuang.tauceti.tools.basic.collection.CollectionKit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class ControllerUtil {
    public static void querySelf(RowQuery rowQuery, String loginUser) {
        TextFilter filter = new TextFilter();
        filter.setField("creator");
        filter.setOption("equals");
        filter.setValue(loginUser);
        RowQuery.Filter[] fs = CollectionKit.append(rowQuery.getFilters(), filter);
        rowQuery.setFilters(fs);
    }

    public static <T, C extends Collection<T>> List<C> split(C c, int len, Supplier<C> getter) {
        List<C> result = new ArrayList<>();
        C tmp = getter.get();
        for (T t : c) {
            tmp.add(t);
            if(tmp.size() == len) {
                result.add(tmp);
                tmp = getter.get();
            }
        }

        if(!tmp.isEmpty()) {
            result.add(tmp);
        }
        return result;
    }
}
