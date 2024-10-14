package tr.com.huseyinaydin.condition;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */


public class EnableDevDataSource implements DataSourceConfig{

    @Override
    public void makeConnection() {
        System.out.println("Varsayılan veritabanına bağlantı kuruldu.");
    }
}