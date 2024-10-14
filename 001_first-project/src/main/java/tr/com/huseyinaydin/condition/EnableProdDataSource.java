package tr.com.huseyinaydin.condition;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */


public class EnableProdDataSource implements DataSourceConfig{

    @Override
    public void makeConnection() {
        System.out.println("Ürün verileri için bağlantı kuruldu.");
    }
}