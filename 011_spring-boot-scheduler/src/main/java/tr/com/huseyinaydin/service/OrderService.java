package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.huseyinaydin.repository.OrderRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @PostConstruct
    public void initDataToDB() {
        List<Order> orders = IntStream.rangeClosed(1, 20)
                .mapToObj(i ->
                        new Order("siparis " + i + ".", new Random().nextInt(5), new Random().nextDouble())).collect(Collectors.toList());
        orderRepository.saveAll(orders);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}