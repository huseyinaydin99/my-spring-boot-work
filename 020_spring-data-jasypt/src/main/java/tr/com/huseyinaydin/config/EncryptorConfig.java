package tr.com.huseyinaydin.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Configuration
public class EncryptorConfig {

    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor getSensitiveInfoEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("toor");// private key - özel anahtar
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations(1000);
        config.setPoolSize(1);
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        //System.out.println(encryptor.encrypt("root"));
        return encryptor;
    }

    public static void main(String[] args) {
        new EncryptorConfig().getSensitiveInfoEncryptor();

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("toor");// private key - özel anahtar
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations(1000);
        config.setPoolSize(1);
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        //EncryptorConfig config = new EncryptorConfig();
        //StringEncryptor encryptor = config.getSensitiveInfoEncryptor();
        String decryptedText = encryptor.decrypt("7wuPIYwjVYqepPGwlFcNug==");
        String decryptedText2 = encryptor.decrypt("iGCvN4fW7Tgj149WMJES8bwp0gFXYK39==");
        System.out.println("De kripte edilen text(metin): " + decryptedText);
        System.out.println("De kripte edilen text(metin): " + decryptedText2);

        String password = "toor"; // Şifreleme anahtarım
        String text1 = "root";
        String text2 = "toor";

        String encryptedText1 = encryptText(text1, password);
        String encryptedText2 = encryptText(text2, password);

        System.out.println("En kripte edilmiş hali 'root': " + encryptedText1);
        System.out.println("En kripte edilmiş hali 'toor': " + encryptedText2);
    }

    public static String encryptText(String text, String password) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password); // Şifreleme anahtarım
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations(1000);
        config.setPoolSize(1);
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        return encryptor.encrypt(text);
    }
}
/*
Bu kodda, şifreli bilgilerin güvenli bir şekilde saklanabilmesi için bir "şifreleyici" yapılandırması yapıyorum. EncryptorConfig isimli bir yapılandırma sınıfı oluşturuyorum ve burada hassas bilgileri şifrelemek için bir StringEncryptor bean tanımlıyorum.

getSensitiveInfoEncryptor metodunda, Jasypt kütüphanesinin sağladığı PooledPBEStringEncryptor sınıfını kullanarak bir şifreleme nesnesi oluşturuyorum. Ardından, SimpleStringPBEConfig ile bu şifreleyicinin özelliklerini yapılandırıyorum:

setPassword("toor") ile şifreleme ve çözme işlemlerinde kullanılacak özel anahtarı (private key) belirliyorum.
setAlgorithm("PBEWithMD5AndDES") diyerek, şifreleme algoritması olarak "PBEWithMD5AndDES" seçiyorum.
setKeyObtentionIterations(1000) ile şifreleme anahtarının türetilmesi için 1000 tekrar yapılmasını sağlıyorum.
setPoolSize(1) ile havuz boyutunu ayarlıyorum; bu, şifreleme performansını ayarlamak için kullanılabilecek bir parametre.
setProviderName("SunJCE") diyerek şifreleme sağlayıcısını "SunJCE" olarak belirliyorum.
setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator") ile şifrelemeye güvenlik katmanı ekleyen rastgele bir "salt" kullanıyorum.
setStringOutputType("base64") ile şifreleme sonucunun Base64 formatında üretilmesini sağlıyorum.
Son olarak, bu yapılandırmayı encryptor nesnesine aktarıp yapılandırılmış bir StringEncryptor olarak geri döndürüyorum. Bu yapı, örneğin veritabanı kullanıcı adı ya da parolası gibi hassas bilgilerin güvenli bir şekilde saklanmasını sağlıyor.

Yorum satırında System.out.println(encryptor.encrypt("root")); satırı bulunuyor. Bunu açarsam, "root" kelimesini şifrelenmiş bir şekilde konsola yazdırıyor.
 */