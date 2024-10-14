package tr.com.huseyinaydin;

import tr.com.huseyinaydin.condition.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@SpringBootApplication
public class FirstProjectApplication implements CommandLineRunner{

	@Autowired
	private DataSourceConfig dataSourceConfig;

	public static void main(String[] args) {
		SpringApplication.run(FirstProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataSourceConfig.makeConnection();
	}
}