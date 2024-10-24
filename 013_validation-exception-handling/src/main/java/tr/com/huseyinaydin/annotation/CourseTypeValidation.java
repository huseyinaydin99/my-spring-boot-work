package tr.com.huseyinaydin.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CourseTypeValidator.class)
public @interface CourseTypeValidation {

    String message() default "Kurs tipi canlı yayın veya önceden kayıt ettirilmiş olmalıdır hacım.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}