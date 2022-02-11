package fr.iut.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Julian on 02/02/2022.
 */
public class NameValidator implements ConstraintValidator<Name, String> {
    @Override
    public void initialize(Name name) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return (s.length() > 2 && s.length() < 20 && s.matches("^[a-zA-Z]+$"));
    }
}
