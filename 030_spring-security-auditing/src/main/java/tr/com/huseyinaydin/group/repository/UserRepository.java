package tr.com.huseyinaydin.group.repository;

import tr.com.huseyinaydin.group.entity.User;
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

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);
}