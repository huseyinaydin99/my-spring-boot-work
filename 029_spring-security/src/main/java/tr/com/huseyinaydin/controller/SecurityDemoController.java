package tr.com.huseyinaydin.controller;

import org.springframework.web.bind.annotation.GetMapping;
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
public class SecurityDemoController {

    @GetMapping("/welcome")
    //ADMIN
    public String welcome() {
        return "Hoşgeldiniz. Buraya sadece adminler yani ADMIN yetkisine sahip olanlar erişebilir.";
    }

    @GetMapping("/text")
    //USER
    public String greeting() {
        return "Buraya sadece normal kullanıcılar yani USER yetkisine sahip olanlar erişebilir.";
    }

    @GetMapping("/nonsecureapp")
    public String nonSecure() {
        return "This is non secure endpoint , you can access it no worries !";
    }
}