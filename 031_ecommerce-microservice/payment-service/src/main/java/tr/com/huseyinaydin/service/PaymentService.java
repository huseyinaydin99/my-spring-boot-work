package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.Payment;
import tr.com.huseyinaydin.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Microservices.
 *
 */

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public Payment getByOrderId(String orderId) {
        return repository.findByOrderId(orderId);
    }
}