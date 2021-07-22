package com.example.employee.error.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class BloodTypeValidator implements ConstraintValidator<BloodType, String> {

    List<String> bloodTypes = Arrays.asList("I", "II", "III", "IV");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return bloodTypes.contains(value);

    }
}
