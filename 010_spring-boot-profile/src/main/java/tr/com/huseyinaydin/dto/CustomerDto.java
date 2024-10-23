package tr.com.huseyinaydin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String dob; //dd/MM/yyyy
}