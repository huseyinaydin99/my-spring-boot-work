package tr.com.huseyinaydin.group;

import tr.com.huseyinaydin.group.common.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class GroupServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupServiceApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorAware(){
		return new AuditorAwareImpl();
	}
}

/*
Spring Security'de Auditing, bir uygulamadaki güvenlikle ilgili olayların ve kullanıcı aktivitelerinin
izlenmesi ve kaydedilmesi sürecidir. Genellikle hangi kullanıcının ne zaman, hangi işlemi gerçekleştirdiği
gibi bilgileri takip etmek için kullanılır. Bu, sistemdeki işlemlerin şeffaflığını artırır ve gerektiğinde
geçmişe yönelik incelemeler yapmayı mümkün kılar.
 */