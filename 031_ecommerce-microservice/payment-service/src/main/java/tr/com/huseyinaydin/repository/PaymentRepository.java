package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Microservices.
 *
 */

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findByOrderId(String orderId);
}