package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleEmail(EmailRequest request) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(request.getToEMail());
        mailMessage.setSubject(request.getSubject());
        mailMessage.setText(request.getMessageBody());
        javaMailSender.send(mailMessage);
        return "E-posta " + request.getToEMail() + " adresine başarıyla gönderildi.";
    }

    public String sendEmailWithAttachment(EmailRequest request) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(sender);
        helper.setTo(request.getToEmails());
        helper.setSubject(request.getSubject());
        helper.setText(request.getMessageBody());

        FileSystemResource file = new FileSystemResource(new File(request.getAttachment()));
        helper.addAttachment(file.getFilename(), file);

        javaMailSender.send(mimeMessage);
        return "Ataç edilmiş belge maili gönderili. İlgili dosya adı: " + file.getFilename();
    }
}
