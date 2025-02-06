package com.xiaomo.anno;

import com.xiaomo.validation.StateValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//元注解
@Documented
//元注解 Target 声明该注解可以用在哪些地方，包括 方法、变量属性、注解、构造方法、参数等
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
// 元注解 声明该注解会在哪个阶段保留（编译、源码、运行等）
@Retention(RetentionPolicy.RUNTIME)
// 指定提供校验规则的类
@Constraint(validatedBy = {StateValidation.class})
public @interface State {

    // 校验失败之后的返回信息
    String message() default "state参数的值只能是已发布或者草稿";

    // 指定分组
    Class<?>[] groups() default {};

    // 载荷 获取到state注解的附加信息（不常用）
    Class<? extends Payload>[] payload() default {};

}
