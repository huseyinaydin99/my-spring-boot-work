package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.annotation.TrackExecutionTime;
import tr.com.huseyinaydin.entity.Product;
import tr.com.huseyinaydin.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService service;

    //öncesi
    @PostMapping
    //@LogRequestAndResponse
    @TrackExecutionTime
    public Product addProduct(@RequestBody Product product) {
        if (product.getPrice() <= 100) {
            throw new RuntimeException("Ürün fiyatı 100'den küçük olamaz.");
        }
        Product saveProduct = service.saveProduct(product);
        return saveProduct;
    }

    //öncesi veya return öncesi
    //hata fırlatma öncesi
    //around advice: sarmalama(öncesi, sonrası)
    @GetMapping
    @TrackExecutionTime
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) throws Exception {
        return service.getProductById(id);
    }

    @PutMapping("/{id}")
    //@LogRequestAndResponse
    public Product updateProduct(@PathVariable int id, @RequestBody Product productRequest) {
        return service.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    @TrackExecutionTime
    public long deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }
}