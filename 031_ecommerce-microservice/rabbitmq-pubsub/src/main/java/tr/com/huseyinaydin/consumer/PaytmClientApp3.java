package tr.com.huseyinaydin.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.config.MessagingConfig;
import tr.com.huseyinaydin.dto.PaymentRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot, Microservices.
 * @since 1994
 */

@Service
public class PaytmClientApp3 {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void processPaymentRequest(PaymentRequest request) {
        try {
            System.out.println("3. tüketici. Tüketilen: " + new ObjectMapper().writeValueAsString(request));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}