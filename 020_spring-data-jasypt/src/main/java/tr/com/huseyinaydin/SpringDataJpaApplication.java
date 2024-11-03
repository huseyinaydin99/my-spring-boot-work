package tr.com.huseyinaydin;

import tr.com.huseyinaydin.common.AuditorAwareImpl;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@EnableEncryptableProperties
/*
@EnableEncryptableProperties anotasyonu, Jasypt kütüphanesini kullanarak
konfigürasyon dosyalarındaki (örneğin application.properties veya application.yml)
şifrelenmiş özelliklerin otomatik olarak çözülmesini sağlar. Bu anotasyonu eklediğimde,
ENC(...) formatında şifrelenmiş herhangi bir değer, uygulama çalışırken otomatik olarak
çözülerek kullanıma hazır hale gelir.
 */
public class SpringDataJpaApplication {

	@Bean
	public AuditorAware<String> auditorAware(){
		return new AuditorAwareImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}
}