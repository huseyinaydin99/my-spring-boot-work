package tr.com.huseyinaydin.respository;

import tr.com.huseyinaydin.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByName(String name);

    List<Product> findByProductType(String productType);

    List<Product> findByPriceAndProductType(double price, String productType);

    //@Query(value = "SELECT * FROM PRODUCT_TBL WHERE price = ?1", nativeQuery = true)
    @Query("from Product p where p.price = ?1 ")

    //@Query("from PRODUCT_TABLE p where p.price= :price")
    List<Product> getProductByPrice(double price);

    //ön ek + kolon + operator

    List<Product> findByPriceIn(List<Double> prices);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    List<Product> findByPriceGreaterThan(double price);

    List<Product> findByPriceLessThan(double price);

    List<Product> findByNameIgnoreCaseContaining(String name);
}