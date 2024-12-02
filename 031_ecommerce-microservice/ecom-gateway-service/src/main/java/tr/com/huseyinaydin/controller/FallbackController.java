package tr.com.huseyinaydin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Microservices.
 *
 */

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/order")
    public ResponseEntity<String> orderFallback(){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("Sipariş hizmetinde sorun var sistem arızalı veya kapalı.");
    }

    @GetMapping("/payment")
    public ResponseEntity<String> paymentFallback(){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("Ödeme hizmetinde sorun var sistem arızalı veya kapalı.");
    }

    @GetMapping("/user")
    public ResponseEntity<String> userFallback(){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("Kullanıcı hizmetinde sorun var sistem arızalı veya kapalı.");
    }
}