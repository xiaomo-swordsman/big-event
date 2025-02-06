package com.xiaomo.validation;

import com.xiaomo.anno.State;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// ConstraintValidator<给哪个注解提供校验规则，校验的数据类型>
public class StateValidation implements ConstraintValidator<State,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if(value == null){
            return false;
        }

        if(value.equals("已发布") || value.equals("草稿")){
            return true;
        }
        return false;
    }
}
