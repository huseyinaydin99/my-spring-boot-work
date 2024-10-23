package tr.com.huseyinaydin.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

public class CourseTypeValidator implements ConstraintValidator<CourseTypeValidation, String> {

    @Override
    public boolean isValid(String courseType, ConstraintValidatorContext constraintValidatorContext) {
        List list = Arrays.asList("CANLI", "KAYDEDILMIS");
        return list.contains(courseType);
    }
}