package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.PaymentRequest;
import tr.com.huseyinaydin.publisher.PaytmPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot, Microservices.
 * @since 1994
 */

@RestController
public class PaytmController {

    @Autowired
    private PaytmPublisher publisher;

    @PostMapping("/mq/pay")
    public String payUsingAPI(@RequestBody PaymentRequest request) {
        publisher.doPaymentTransaction(request);
        return "Ödeme isteği işleniyor!";
    }
}