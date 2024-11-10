package tr.com.huseyinaydin.hash;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("CustomersInfo")
/*
@RedisHash("CustomersInfo") anotasyonu, Redis veri tabanında bu sınıfın nesnelerinin "CustomersInfo" adlı
bir hash yapısında tutulmasını sağlar. Bu şekilde Redis, her bir nesneyi anahtar-değer çifti olarak
depolar ve hızlı erişim sağlar.
*/
public class Customer implements Serializable {

    private static final long serialVersionUID= 1L;

    @Id
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dob;

    private String phone;
}