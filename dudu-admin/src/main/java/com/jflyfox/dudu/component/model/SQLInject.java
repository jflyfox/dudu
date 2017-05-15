package com.jflyfox.dudu.component.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * URL 处理
 * Created by flyfox 369191470@qq.com on 2017/4/28.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInject {

    boolean value() default true; // false不检测

}
