package tr.com.huseyinaydin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

@Entity
@Table(name = "PAYMENT_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String payMode;
    private double amount;
    private Date paidDate;
    private int userId;
    private String orderId;
    private String paymentStatus;
}