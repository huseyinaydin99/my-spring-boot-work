package tr.com.huseyinaydin.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
public class AppConfig {

//    @Bean
//    @ConditionalOnProperty(name="prod.datasource.enabled", havingValue="true")
//    @Primary
//    public DataSourceConfig enableProdDatabase() {
//        return new EnableProdDataSource();
//    }

    @Bean
    @ConditionalOnMissingBean(EnableProdDataSource.class) //bu sınıftan yoksa EnableDevDataSource'u oluştur IoC Container'de.
    public EnableDevDataSource enableDevDatabase() {
        return new EnableDevDataSource();
    }
}