package tr.com.huseyinaydin.exception;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

public class CourseServiceBusinessException extends RuntimeException{
    public CourseServiceBusinessException(String message) {
        super(message);
    }
}