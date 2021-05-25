package com.chuang.anarres.web.starter;

import com.chuang.tauceti.rowquery.handlers.DefaultValueGetter;
import com.chuang.tauceti.rowquery.handlers.ValueGetter;

import java.util.Optional;
import java.util.function.Supplier;

public interface OperatorManager extends ValueGetter<String> {

    static OperatorManager create(Supplier<Optional<String>> getter) {
        return new Impl(getter);
    }

    class Impl extends DefaultValueGetter<String> implements OperatorManager {

        public Impl(Supplier<Optional<String>> getter) {
            super(getter);
        }
    }
}
