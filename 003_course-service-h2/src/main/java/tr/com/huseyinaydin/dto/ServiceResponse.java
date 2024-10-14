package tr.com.huseyinaydin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse<T> {

    private HttpStatus status;
    private T response;
}