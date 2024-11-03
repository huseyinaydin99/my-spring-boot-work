package tr.com.huseyinaydin;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public class SpringDataJpaApplicationTests {

	@Test
	public void getSensitiveInfoEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("toor");// private -key
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations(1000);
		config.setPoolSize(1);
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		System.out.println(encryptor.encrypt("Hüseyin AYDIN"));
	}
}
/*
Bu test sınıfında, "Hüseyin AYDIN" ifadesini güvenli hale getirmek için bir şifreleyici oluşturdum.
İlk olarak, PooledPBEStringEncryptor nesnesini ayarladım ve ardından SimpleStringPBEConfig ile
şifreleme algoritmasını ve özel anahtarı yapılandırdım. Son olarak, "toor" anahtarını kullanarak
metni şifreleyip şifrelenmiş sonucu ekrana yazdırdım.
*/