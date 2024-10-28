package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}