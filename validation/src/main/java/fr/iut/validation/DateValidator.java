package fr.iut.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Julian on 02/02/2022.
 */
public class DateValidator implements ConstraintValidator<Date, java.util.Date> {

    @Override
    public void initialize(Date date) {

    }

    @Override
    public boolean isValid(java.util.Date date, ConstraintValidatorContext constraintValidatorContext) {
        // Cas où la date est facultative
        if(date == null) return true;
        return date.toString().matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$"); // format YYYY-MM-DD
    }
}
