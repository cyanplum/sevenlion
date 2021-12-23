package cn.sevenlion.config.annotation;

import cn.sevenlion.config.selector.CyanplumSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author: qimeiwen
 * @create: 2021-11-30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CyanplumSelector.class)
public @interface EnableCyanplum {

    boolean baseConfig() default true;

    boolean knife4j() default false;

    boolean swagger() default false;

    boolean oss() default false;

    boolean redis() default false;

    boolean resultHandler() default false;

}
