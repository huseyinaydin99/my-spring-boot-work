package tr.com.huseyinaydin.dao;

import org.springframework.data.repository.CrudRepository;
import tr.com.huseyinaydin.entity.CourseEntity;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

public interface CourseDao extends CrudRepository<CourseEntity,Integer> {

}