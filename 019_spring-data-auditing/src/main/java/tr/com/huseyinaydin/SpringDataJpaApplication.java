package tr.com.huseyinaydin;

import tr.com.huseyinaydin.common.AuditorAwareImpl;
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
//@EnableJpaAuditing(auditorAwareRef = "auditorAware") anotasyonu, Spring Boot uygulamamda JPA Auditing özelliğini etkinleştirmek için kullanılır ve auditorAware referansını belirleyerek, güncel denetleyici bilgisinin hangi sınıftan alınacağını tanımlar. Bu, veritabanındaki kayıtların ne zaman ve kim tarafından oluşturulduğunu veya güncellendiğini otomatik olarak izlememi sağlar.
@EnableJpaAuditing(auditorAwareRef = "auditorAware")

//@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class) anotasyonu ise, Envers ile birlikte audit işlemlerini destekleyen özel repository sınıflarını kullanmamı sağlar. Bu sayede, hem standart JPA işlemlerini hem de revizyon kayıtlarını yönetme yeteneğini bir arada elde ederim. Bu iki anotasyon sayesinde, uygulamamda etkili bir audit ve repository yönetimi gerçekleştirmiş oldum.
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class SpringDataJpaApplication {

	@Bean
	public AuditorAware<String> auditorAware(){
		return new AuditorAwareImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}
}