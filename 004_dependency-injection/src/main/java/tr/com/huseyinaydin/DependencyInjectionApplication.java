package tr.com.huseyinaydin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@SpringBootApplication
public class DependencyInjectionApplication implements CommandLineRunner {

	//approach : 2
	//@PostConstruct
	//public void preInitialize(){
		//System.out.println("jdbc ile bağlantı");
	//}

	public static void main(String[] args) {
	 	SpringApplication.run(DependencyInjectionApplication.class, args);
		System.out.println("main metodu ve thread'ı başladı.");
		//UserApp app = context.getBean(UserApp.class);
		//app.loadUserFeeds();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("komut satırı interface run metodu.");
		System.out.println("JDBC bağlantısı ile database bağlantısı kuruldu.");
	}
}
