package tr.com.huseyinaydin.common;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */
/*
AuditorAwareImpl sınıfını oluşturarak, Spring Boot uygulamamda Auditing işlemleri için geçerli denetleyici bilgisini sağlamaya karar verdim. AuditorAware<String> arayüzünü implement ederek, getCurrentAuditor metodunu tanımladım ve bu metod her çağrıldığında "Hüseyin" adını döndürerek, veri tabanında kaydedilen değişikliklerin kim tarafından yapıldığını belirtmiş oldum. Bu sayede, uygulamamda denetleyici bilgilerini otomatik bir şekilde saklayabiliyorum.
*/
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Hüseyin");
    }
}