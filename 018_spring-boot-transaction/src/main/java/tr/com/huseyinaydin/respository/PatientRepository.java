package tr.com.huseyinaydin.respository;

import tr.com.huseyinaydin.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

public interface PatientRepository extends JpaRepository<Patient, Long> {

}