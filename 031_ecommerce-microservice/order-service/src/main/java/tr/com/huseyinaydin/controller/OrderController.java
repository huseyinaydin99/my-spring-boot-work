package tr.com.huseyinaydin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.dto.OrderResponseDTO;
import tr.com.huseyinaydin.entity.Order;
import tr.com.huseyinaydin.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Microservices.
 *
 */

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService service;

    //Validation - kullanıcıdan gelen bilgileri doğrulama yani doğru formatta olup olmadığını sorgulama.
    //logging - loglama yani uygulamanın sehir defteri.
    //Exception handling - istisnaları işleme, hataları yakalama.
    @PostMapping
    public String placeNewOrder(@RequestBody Order order) {
        return service.placeAnOrder(order);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrder(@PathVariable String orderId) throws JsonProcessingException {
        log.info("OrderController::getOrder {} Id'li sipariş bilgileri. ", orderId);
        OrderResponseDTO orderResponseDTO = service.getOrder(orderId);
        log.info("OrderController::getOrder cevabı {} ", new ObjectMapper().writeValueAsString(orderResponseDTO));
        return orderResponseDTO;
    }
}