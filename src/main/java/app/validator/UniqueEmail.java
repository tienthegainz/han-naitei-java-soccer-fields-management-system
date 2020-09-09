package app.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Documented
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface UniqueEmail {

    String message() default "The email is already registered";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
