package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.annotation.TrackExecutionTime;
import tr.com.huseyinaydin.entity.Product;
import tr.com.huseyinaydin.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    //@LogRequestAndResponse
    @TrackExecutionTime
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @TrackExecutionTime
    public List<Product> getProducts() {
        return repository.findAll();
    }

    //@LogRequestAndResponse
    public Product getProductById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ürün bulunamadı " + id));
    }

    @TrackExecutionTime
    public Product updateProduct(int id, Product productRequest) {
        // Id'ye göre güncellenecek ürünü get et.
        // DB'deki halini yeni hali ile güncelle.
        Product existingProduct = repository.findById(id).get(); // okuma Id'ye göre
        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setProductType(existingProduct.getProductType());
        return repository.save(existingProduct);
    }

    public long deleteProduct(int id) {
        repository.deleteById(id);
        return repository.count();
    }
}