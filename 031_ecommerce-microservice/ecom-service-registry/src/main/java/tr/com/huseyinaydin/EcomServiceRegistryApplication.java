package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Microservices.
 *
 */

@SpringBootApplication
@EnableEurekaServer
public class EcomServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomServiceRegistryApplication.class, args);
	}
}
/*
@EnableEurekaServer dipnotu, uygulamayı Eureka tabanlı bir servis kayıt ve keşif sunucusu olarak çalıştırmak için kullanılır.
Bu sayede diğer mikroservisler, Eureka'ya kaydolup birbirlerini dinamik olarak keşfedebilirler.
*/