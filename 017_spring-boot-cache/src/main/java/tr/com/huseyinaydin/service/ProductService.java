package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.Product;
import tr.com.huseyinaydin.respository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository repository;

    //@CachePut(key = "#product.id") dipnotu, bir metod çalıştığında çıkan sonucu, ürünün id numarasını kullanarak önbelleğe ekliyor veya güncelliyor. Böylece, her çağırdığımda güncel veriyi önbellekte saklamış oluyorum.
    @CachePut(key = "#product.id")
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    //@Cacheable dipnotu, bir metodun sonucunu önbelleğe kaydedip sonraki çağrılarda aynı işlemi tekrar yapmadan doğrudan önbellekten çekmemi sağlıyor. Böylece, veriye daha hızlı erişip performansı artırmış oluyorum.
    @Cacheable
    public List<Product> getProducts() {
        log.info("ProductService::getProducts() veritabanına bağlanıldı");
        return repository.findAll();
    }

    @Cacheable(key = "#id")
    public Product getProductById(int id) {
        log.info("ProductService::getProductById() veritabanına bağlanıldı");
        return repository.findById(id).get();
    }

    public Product getProductByName(String name) {
        return repository.findByName(name);
    }

    public List<Product> getProductsByType(String productType) {
        return repository.findByProductType(productType);
    }

    public List<Product> getProductWithPriceAndType(double price, String productType) {
        return repository.findByPriceAndProductType(price, productType);
    }

    public List<Product> getProductsByPrice(double price) {
        return repository.getProductByPrice(price);
    }

    //gelen Id'yi önbellekler
    @CachePut(key = "#id")
    public Product updateProduct(int id, Product productRequest) {
        // ürünü Id'ye göre GET et.
        // veritabanındaki halini yeni hali ile değiştir.
        Product existingProduct = repository.findById(id).get(); // veritabanındaki hali
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setProductType(existingProduct.getProductType());
        return repository.save(existingProduct);
    }

    //@CacheEvict(key = "#id") dipnotu, önbellekteki belirli bir veriyi id değeri üzerinden silmemi sağlıyor. Bu sayede, önbellekte güncel olmayan veriyi temizleyip sistemde doğruluğu korumuş oluyorum.
    @CacheEvict(key = "#id")
    public long deleteProduct(int id) {
        repository.deleteById(id);
        return repository.count();
    }

    public List<Product> getProductsByMultiplePriceValue(List<Double> prices) {
        return repository.findByPriceIn(prices);
    }

    public List<Product> getProductsWithinPriceRange(double minPrice, double maxPrice) {
        return repository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> getProductsWithHigherPrice(double price) {
        return repository.findByPriceGreaterThan(price);
    }

    public List<Product> getProductsWithLessPrice(double price) {
        return repository.findByPriceLessThan(price);
    }

    public List<Product> getProductsWithLike(String name) {
        return repository.findByNameIgnoreCaseContaining(name);
    }

    //sıralama
    public List<Product> getProductsWithSorting(String fieldName) {
        return repository.findAll(Sort.by(Sort.Direction.ASC, fieldName));
    }

    //sayfalama
    public Page<Product> getProductsWithPageResponse(int offset, int limit) {
        return repository.findAll(PageRequest.of(offset, limit));
    }

    //sayfalama ve sıralama
    public Page<Product> getProductsWithSortingAndPagination(String fieldName, int offset, int limit) {
        return repository.findAll(PageRequest.of(offset, limit).withSort(Sort.by(fieldName)));
    }
}