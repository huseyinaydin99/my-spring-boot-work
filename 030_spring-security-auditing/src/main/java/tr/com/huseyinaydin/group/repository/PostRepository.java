package tr.com.huseyinaydin.group.repository;

import tr.com.huseyinaydin.group.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public interface PostRepository extends JpaRepository<Post, Integer> {
}