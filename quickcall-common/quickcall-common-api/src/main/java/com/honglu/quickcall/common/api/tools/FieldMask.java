package com.honglu.quickcall.common.api.tools;

import java.lang.annotation.*;

/**
 * Created by len.song on 2017-12-07.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldMask {
    int prelen() default -1;

    int suflen() default -1;

    String mask() default "****";
}