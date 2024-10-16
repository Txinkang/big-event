package com.tang.bigevent.anno;

import com.tang.bigevent.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented//元注解，让state注解可以被抽取到帮助文档里
@Target({ElementType.FIELD})//元注解，指定state注解能被谁使用，属性或者类...等
@Retention(RetentionPolicy.RUNTIME)//指定注解可以在什么状态下运行，编译阶段，打包，或者运行等
@Constraint(validatedBy = {StateValidation.class})//指定与哪个规则连接
public @interface State {
    String message() default "state参数值只能是已发布或者草稿";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
