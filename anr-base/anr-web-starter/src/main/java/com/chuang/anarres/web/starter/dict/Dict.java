package com.chuang.anarres.web.starter.dict;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Dict.List.class)
@Documented
@Constraint(
        validatedBy = {}
)
public @interface Dict {
    String message() default "{javax.validation.constraints.Dict.message}";

    String dictType();

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Dict[] value();
    }
}
