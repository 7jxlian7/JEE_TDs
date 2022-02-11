package fr.iut.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Julian on 02/02/2022.
 */
public class LoginValidator implements ConstraintValidator<Login, String> {
    @Override
    public void initialize(final Login login) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return (s.length() > 2 && s.length() < 8 && s.matches("^[a-zA-Z]+$"));
    }
}
