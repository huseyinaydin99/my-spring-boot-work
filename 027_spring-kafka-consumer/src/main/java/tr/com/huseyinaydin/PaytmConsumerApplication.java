package tr.com.huseyinaydin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.dto.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@SpringBootApplication
@Slf4j
public class PaytmConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaytmConsumerApplication.class, args);
    }

    @KafkaListener(topics = "PAYMENT_TOPIC2", groupId = "odeme_tuketici_grup")
    public void paymentConsumer1(PaymentRequest paymentRequest) throws JsonProcessingException {
        //business logic - iş mantığı hesap kitap algoritma...
        log.info("paymentConsumer1 mesaj tüketildi. {} ", new ObjectMapper().writeValueAsString(paymentRequest));
    }

    @KafkaListener(topics = "PAYMENT_TOPIC2", groupId = "odeme_tuketici_grup")
    public void paymentConsumer2(PaymentRequest paymentRequest) throws JsonProcessingException {
        //business logic - iş mantığı hesap kitap algoritma...
        log.info("paymentConsumer2 mesaj tüketildi. {} ", new ObjectMapper().writeValueAsString(paymentRequest));
    }

    @KafkaListener(topics = "PAYMENT_TOPIC2", groupId = "odeme_tuketici_grup")
    public void paymentConsumer3(PaymentRequest paymentRequest) throws JsonProcessingException {
        //business logic - iş mantığı hesap kitap algoritma...
        log.info("paymentConsumer3 mesaj tüketildi. {} ", new ObjectMapper().writeValueAsString(paymentRequest));
    }

    @KafkaListener(topics = "PAYMENT_TOPIC2", groupId = "odeme_tuketici_grup")
    public void paymentConsumer4(PaymentRequest paymentRequest) throws JsonProcessingException {
        //business logic - iş mantığı hesap kitap algoritma...
        log.info("paymentConsumer4 mesaj tüketildi. {} ", new ObjectMapper().writeValueAsString(paymentRequest));
    }
}