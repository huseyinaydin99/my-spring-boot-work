package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.PatientAppointmentRequest;
import tr.com.huseyinaydin.service.PractoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@RestController
@RequestMapping("/practo")
public class PractoController {

    @Autowired
    private PractoService service;

    @PostMapping("/bookAppointment")
    public String bookDoctorsAppointment(@RequestBody PatientAppointmentRequest request){
        return service.bookAppointment(request);
    }
}