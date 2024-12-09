package tr.com.huseyinaydin.publisher;

import tr.com.huseyinaydin.config.MessagingConfig;
import tr.com.huseyinaydin.dto.PaymentRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot, Microservices.
 * @since 1994
 */

@Service
public class PaytmPublisher {

    @Autowired
    private RabbitTemplate template;

    public void doPaymentTransaction(PaymentRequest request){
        request.setTransactionId(UUID.randomUUID().toString());
        request.setTxDate(new Date());
        template.convertAndSend(MessagingConfig.EXCHANGE,
                MessagingConfig.ROUTING_KEY, request);
    }
}