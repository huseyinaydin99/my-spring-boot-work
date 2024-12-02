package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot, Microservices.
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class EcomConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomConfigServerApplication.class, args);
	}
}

/*
@EnableDiscoveryClient dipnotu, mikroservislerin servis kayıt defterine (Eureka gibi) kaydolmasını ve diğer servisleri keşfetmesini sağlar.
Bu sayede servisler, dinamik olarak birbirlerini bulabilir ve bağlantı kurabilir.
@EnableConfigServer dipnotu, merkezi bir konfigürasyon sunucusu oluşturmak için kullanılır.
Mikroservisler, ayarlarını bu sunucudan alarak merkezi yönetim ve kolay yapılandırma güncellemesi sağlar.
*/