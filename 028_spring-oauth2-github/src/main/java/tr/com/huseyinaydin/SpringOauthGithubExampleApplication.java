package tr.com.huseyinaydin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@SpringBootApplication
@RestController
public class SpringOauthGithubExampleApplication {

    @GetMapping("/info")
    public String getUserInfoFromGitHub(Principal principal) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(principal.getName()) + " Hoş geldiniz, giriş yapıldı.";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringOauthGithubExampleApplication.class, args);
    }
}