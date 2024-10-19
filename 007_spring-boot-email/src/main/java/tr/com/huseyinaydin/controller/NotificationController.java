package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.EmailRequest;
import tr.com.huseyinaydin.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@RestController
public class NotificationController {

    @Autowired
    private EmailService service;

    @PostMapping("/sendEmail")
    public String sendTextEmail(@RequestBody EmailRequest request) {
        return service.sendSimpleEmail(request);
    }

    @PostMapping("/sendAttachment")
    public String sendEmailWithAttachment(@RequestBody EmailRequest request) throws MessagingException {
        return service.sendEmailWithAttachment(request);
    }
}