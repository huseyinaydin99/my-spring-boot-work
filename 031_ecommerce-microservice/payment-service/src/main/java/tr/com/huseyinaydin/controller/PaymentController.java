package tr.com.huseyinaydin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.entity.Payment;
import tr.com.huseyinaydin.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/payments")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping("/{orderId}")
    public Payment getPayment(@PathVariable String orderId) throws JsonProcessingException {
        log.info("PaymentController::getPayment {} Id'li ödemeyi çekme isteği.", orderId);
        Payment paymentResponse = service.getByOrderId(orderId);
        log.info("PaymentController::getPayment ödeme çekme cevabı. {} ", new ObjectMapper().writeValueAsString(paymentResponse));
        return paymentResponse;
    }
}