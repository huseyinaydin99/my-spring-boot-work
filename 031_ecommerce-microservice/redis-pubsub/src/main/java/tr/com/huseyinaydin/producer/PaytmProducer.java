package tr.com.huseyinaydin.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.dto.PaymentRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
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
@AllArgsConstructor
public class PaytmProducer {

    private ChannelTopic channelTopic;

    private StringRedisTemplate redisTemplate;

    public String doTransaction(PaymentRequest paymentRequest){
        try {
            redisTemplate.convertAndSend(channelTopic.getTopic(),
                    new ObjectMapper().writeValueAsString(paymentRequest));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Ödeme isteği üretildi.";
    }
}