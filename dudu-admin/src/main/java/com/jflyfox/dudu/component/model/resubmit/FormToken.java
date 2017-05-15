package com.jflyfox.dudu.component.model.resubmit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 页面form   token
 * <p>
 * Created by flyfox 369191470@qq.com on 2017/4/28.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormToken {

    boolean save() default false;

    boolean remove() default false;
}
