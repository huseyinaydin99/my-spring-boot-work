package tr.com.huseyinaydin.respository;

import tr.com.huseyinaydin.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
}