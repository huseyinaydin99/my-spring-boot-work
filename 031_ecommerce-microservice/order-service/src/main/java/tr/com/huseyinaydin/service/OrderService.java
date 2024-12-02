package tr.com.huseyinaydin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.dto.OrderResponseDTO;
import tr.com.huseyinaydin.dto.PaymentDTO;
import tr.com.huseyinaydin.dto.UserDTO;
import tr.com.huseyinaydin.entity.Order;
import tr.com.huseyinaydin.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot, Microservices.
 * @since 1994
 */

@Service
@RefreshScope
/*@RefreshScope, Spring Cloud Config ile birlikte kullanılarak, uygulamanın konfigürasyon
değerlerini dinamik olarak yenilemesini sağlar. Bu sayede, uygulama yeniden başlatılmadan
yapılandırma değişiklikleri anında uygulanabilir.

@RefreshScope ile işaretlenmiş bir sınıf içerisindeki @Value ile enjekte edilen değerler,
konfigürasyon değiştirildiğinde otomatik olarak yenilenir.

Ancak bunun gerçekleşmesi için Spring Cloud Config ve bir mekanizma olan
/actuator/refresh endpoint'inin kullanılması gerekir.
*/
@Slf4j
public class OrderService {

    public static final String ORDER_SERVICE = "orderService";

    @Autowired
    private OrderRepository repository;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;
    /*
    @Lazy anotasyonu, RestTemplate'in yalnızca ilk ihtiyaç duyulduğunda başlatılmasını sağlar,
    bu da uygulamanın başlatılma süresini kısaltır.

    Bu sayede, performans optimizasyonu sağlarken, sadece gerekli olduğunda dış servislere
    yapılan istekleri gerçekleştiriyorum.
     */

    @Value("${microservice.payment-service.endpoints.fetchPaymentById.uri}")
    private String fetchPaymentUri;

    @Value("${microservice.user-service.endpoints.fetchUserById.uri}")
    private String fetchUserUri;

    @Value("${test.input}")
    private String testValue;

    @Autowired
    private StreamBridge streamBridge;

    public String placeAnOrder(Order order) {
        order.setPurchaseDate(new Date());
        order.setOrderId(UUID.randomUUID().toString().split("-")[0]);
        repository.save(order);
        //kafka kullanarak ödeme servisine gönderdim böylelikle microservice'ler arası iletişim kurdum.
        try {
            //kafkaTemplate.send(topicName, new ObjectMapper().writeValueAsString(order));
            streamBridge.send("orderBinding-out-0", new ObjectMapper().writeValueAsString(order));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return order.getOrderId() + "Id'li siparişiniz verildi onaylandığında bilgilendirileceksiniz.";
    }

    /*
    @CircuitBreaker anotasyonu, belirli bir servisin başarısız olması durumunda devre kesici
    uygulayarak sistemin çökmesini engeller.

    name = ORDER_SERVICE parametresi, devre kesicinin hangi servise ait olduğunu belirlerken,
    fallbackMethod = "getOrderDetails" ile hata durumunda çağrılacak yedek bir metot (fallback) tanımlarım.

    Bu sayede, ana servis başarısız olduğunda sistem yine çalışmaya devam eder ve kullanıcıya
    belirlediğim yedek yöntemi sunarım.
    */
    @CircuitBreaker(name = ORDER_SERVICE, fallbackMethod = "getOrderDetails")
    public OrderResponseDTO getOrder(String orderId) throws JsonProcessingException {
        System.out.println("****** " + testValue);
        System.out.println("fetchPaymentUri(ödeme microservice adresi) : " + fetchPaymentUri + " ve " + "fetchUserUri(kullanıcı microservice adresi): " + fetchUserUri);
        log.info("OrderService::getOrder {} Id'li sipariş getirme isteği. ", orderId);
        Order order = repository.findByOrderId(orderId);
        //Ödeme-> REST payment-service'e HTTP GET isteği.
        PaymentDTO paymentDTO = restTemplate.getForObject(fetchPaymentUri + orderId, PaymentDTO.class);
        log.info("OrderService::getOrder payment-service'den ödeme bilgileri çekiliyor {} ", new ObjectMapper().writeValueAsString(paymentDTO));
        //user-info-> rest call user-service
        UserDTO userDTO = restTemplate.getForObject(fetchUserUri + order.getUserId(), UserDTO.class);
        log.info("OrderService::getOrder user service'den kullanıcı bilgileri çekiliyor {} ", new ObjectMapper().writeValueAsString(userDTO));

        return OrderResponseDTO.builder()
                .order(order)
                .paymentResponse(paymentDTO)
                .userInfo(userDTO)
                .build();
    }

    public OrderResponseDTO getOrderDetails(String orderId, Exception ex) {
        return new OrderResponseDTO("BAŞARISIZ...", null, null, null);
    }
}