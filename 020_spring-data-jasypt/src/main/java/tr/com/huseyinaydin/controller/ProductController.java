package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.entity.Product;
import tr.com.huseyinaydin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @GetMapping
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

    @GetMapping("/name/{name}")
    public Product getProductByName(@PathVariable String name) {
        return service.getProductByName(name);
    }

    @GetMapping("/type/{productType}")
    public List<Product> getProductsByType(@PathVariable String productType) {
        return service.getProductsByType(productType);
    }

    @GetMapping("/price/{price}/type/{productType}")
    public List<Product> getProductWithPriceAndType(@PathVariable double price,@PathVariable String productType) {
        return service.getProductWithPriceAndType(price, productType);
    }

    @GetMapping("/search/{price}")
    public List<Product> getProductByPrice(@PathVariable double price) {
        return service.getProductsByPrice(price);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product productRequest) {
       return service.updateProduct(id, productRequest);
    }

    @DeleteMapping("/{id}")
    public long deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }

    //operator

    @PostMapping("/search")
    public List<Product> getProductsByMultiplePriceValue(@RequestBody List<Double> prices){
        return service.getProductsByMultiplePriceValue(prices);
    }

    @GetMapping("/min/{minPrice}/max/{maxPrice}")
    public List<Product> getProductsWithinPriceRange(@PathVariable double minPrice,@PathVariable double maxPrice){
        return service.getProductsWithinPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/high/{price}")
    public List<Product> getProductsWithHigherPrice(@PathVariable double price){
        return service.getProductsWithHigherPrice(price);
    }

    @GetMapping("/less/{price}")
    public List<Product> getProductsWithLessPrice(@PathVariable double price){
        return service.getProductsWithLessPrice(price);
    }

    @GetMapping("/like/{name}")
    public List<Product> getProductsWithLike(@PathVariable String name){
        return service.getProductsWithLike(name);
    }

    //sayfalama
    @GetMapping("/sort/{fieldName}")
    public List<Product> getProductsWithSorting(@PathVariable String fieldName) {
        return service.getProductsWithSorting(fieldName);
    }

    //sıralama
    @GetMapping("/page/{offset}/{limit}")
    public Page<Product> getProductsWithPageResponse(@PathVariable int offset, @PathVariable int limit) {
        return service.getProductsWithPageResponse(offset, limit);
    }

    //sıralama & sayfalama
    @GetMapping("/pageWithSort/{fieldName}/{offset}/{limit}")
    public Page<Product> getProductsWithSortingAndPagination(@PathVariable String fieldName, @PathVariable int offset, @PathVariable int limit) {
        return service.getProductsWithSortingAndPagination(fieldName, offset, limit);
    }
}