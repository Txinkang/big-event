package com.tang.bigevent.validation;

import com.tang.bigevent.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {
    //重写isValid方法制定规则
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }

        if (value.equals("已发布") || value.equals("草稿")){
            return true;
        }

        return false;
    }
}
