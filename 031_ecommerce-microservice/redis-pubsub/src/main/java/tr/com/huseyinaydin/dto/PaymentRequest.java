package tr.com.huseyinaydin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Microservices.
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    private String transactionId;
    private String srcAc;
    private String destAc;
    private double amount;
    private Date txDate;
}