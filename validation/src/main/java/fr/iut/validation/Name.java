package fr.iut.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Julian on 02/02/2022.
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
public @interface Name {
    String message() default "Name is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
