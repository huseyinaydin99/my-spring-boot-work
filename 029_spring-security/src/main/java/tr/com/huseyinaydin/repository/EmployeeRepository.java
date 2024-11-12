package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUserName(String username);
}