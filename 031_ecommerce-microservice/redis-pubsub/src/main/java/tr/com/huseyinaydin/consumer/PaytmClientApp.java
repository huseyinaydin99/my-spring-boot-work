package tr.com.huseyinaydin.consumer;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot, Microservices.
 * @since 1994
 */

public class PaytmClientApp implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("PaytmClientApp mesaj tüketildi(alındı) -> " + message);
    }
}