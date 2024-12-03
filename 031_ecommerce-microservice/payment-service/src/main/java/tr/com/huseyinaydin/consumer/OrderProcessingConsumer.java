package tr.com.huseyinaydin.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.dto.Order;
import tr.com.huseyinaydin.dto.User;
import tr.com.huseyinaydin.entity.Payment;
import tr.com.huseyinaydin.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.function.Consumer;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Microservices.
 *
 */

@Component
public class OrderProcessingConsumer {

    public static final String USER_SERVICE_URL = "http://localhost:9090/users";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentRepository repository;

    //@KafkaListener(topics = "ORDER_PAYMENT_TOPIC")
    @Bean
    public Consumer<String> processOrder() {
        return msg -> {
            try {
                Order order = new ObjectMapper()
                        .readValue(msg, Order.class);
                //ödeme isteği nesnesini inşa et.
                Payment payment = Payment.builder()
                        .payMode(order.getPaymentMode())
                        .amount(order.getPrice())
                        .paidDate(new Date())
                        .userId(order.getUserId())
                        .orderId(order.getOrderId())
                        .build();
                if (payment.getPayMode().equals("COD")) {
                    payment.setPaymentStatus("ASKIDA");
                } else {
                    //doğrulama - ödeme işlemi
                    User user = restTemplate.getForObject(USER_SERVICE_URL + "/" + payment.getUserId(), User.class);
                    if (payment.getAmount() > user.getAvailableAmount()) {
                        throw new RuntimeException("Yetersiz miktar !");
                    } else {
                        payment.setPaymentStatus("ÖDENDİ");
                        restTemplate.put(USER_SERVICE_URL + "/" + payment.getUserId() + "/" + payment.getAmount(), null);
                    }
                }
                repository.save(payment);
            } catch (JsonProcessingException e) {
                e.printStackTrace();//log
            }
        };
    }
}