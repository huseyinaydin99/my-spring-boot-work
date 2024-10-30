package tr.com.huseyinaydin.dto;

import tr.com.huseyinaydin.entity.Appointment;
import tr.com.huseyinaydin.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientAppointmentRequest {

    private Patient patient;
    private Appointment appointment;
}