package tech.lovelycheng.demo.test.fileimport.easyimport.fo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * @author chengtong
 * @date 2022/6/28 16:05
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {

    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";
}

