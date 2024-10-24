package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public interface OrderRepository extends JpaRepository<Order,Integer> {

}