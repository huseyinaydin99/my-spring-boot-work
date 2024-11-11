package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.PaymentRequest;
import tr.com.huseyinaydin.dto.PaytmRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@RestController
public class PaytmController {

    @Value("${payment.producer.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/publish/{message}")
    public void sendMessage(@PathVariable String message) {
        for (int i = 0; i <= 10000; i++) {
            kafkaTemplate.send(topicName, message + i);
        }
    }

    @PostMapping("/payment")
    public String doPayment(@RequestBody PaytmRequest<PaymentRequest> paytmRequest) {
        PaymentRequest paymentRequest = paytmRequest.getPayload();
        paymentRequest.setTransactionId(UUID.randomUUID().toString());
        paymentRequest.setTxDate(new Date());
        kafkaTemplate.send(topicName, paymentRequest);
        return "ödeme tamamlandı kardeş... :)";
    }
}