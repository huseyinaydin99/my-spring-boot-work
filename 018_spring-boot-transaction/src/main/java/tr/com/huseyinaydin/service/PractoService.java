package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.PatientAppointmentRequest;
import tr.com.huseyinaydin.entity.Appointment;
import tr.com.huseyinaydin.entity.Patient;
import tr.com.huseyinaydin.respository.AppointmentRepository;
import tr.com.huseyinaydin.respository.PatientRepository;
import tr.com.huseyinaydin.util.PromocodeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Service
public class PractoService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Transactional
    public String bookAppointment(PatientAppointmentRequest request) {
        //hasta kaydı
        Patient patient = request.getPatient();
        long patientId = patientRepository.save(patient).getPatientId();
        //randevu kaydı
        Appointment appointment = request.getAppointment();

        //rateHospitality(ratingInfo)

        //validate user promocode
        if (appointment.getPromoCode() != null) {
            PromocodeValidator.validatePromoCode(appointment.getPromoCode());
        }
        appointment.setPatientId(patientId);

        String appointmentNo = appointmentRepository.save(appointment).getAppointmentId();
        return "Merhabalar sayın " + patient.getName() +
                " randevunuz başarı ile alındı, randevu numaranız: "
                + appointmentNo;
    }

    //public void addDoctorRating() {
    //  //veritabanına kaydet
    //}

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void rateHospitality() {
        //save to db
    }
}