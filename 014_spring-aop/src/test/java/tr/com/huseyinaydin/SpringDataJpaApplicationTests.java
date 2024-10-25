package tr.com.huseyinaydin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.controller.ProductController;
import tr.com.huseyinaydin.entity.Product;
import tr.com.huseyinaydin.respository.ProductRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@SpringBootTest
@AutoConfigureMockMvc
public class SpringDataJpaApplicationTests {

    @Autowired
    private ProductController productController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(ProductController.class)
                .build();
    }

    @Test
    public void addProductTest() throws Exception {
        Product demoProduct = new Product(1, "demo", 1000, "demo ürün", "basit ürün");
        when(productRepository.save(any())).thenReturn(demoProduct);

        //URL -> /products -
        //HTTP METHOD : POST -
        //REQ & RESP : Product - (Json String)
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .content(convertObjectAsString(demoProduct))
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void getProductsShouldReturnAllProducts() throws Exception {
        when(productRepository.findAll()).thenReturn(Arrays.asList(
                new Product(1, "demo1", 1000, "demo ürün1", "basit ürün1")
                ,new Product(2, "demo2", 2000, "demo product2", "basit ürün2")));

        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1));
    }

    @Test
    public void getProductByIdTest() throws Exception {
        when(productRepository.findById(108)).thenReturn(Optional.of(new Product(108, "çatal", 200, "açıklama 123", "mutfak")));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products/{id}", 108)
                        .accept("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(108));
    }

    @Test
    public void updateProductTest() throws Exception {
        Product demoProduct = new Product(1, "demo", 1000, "demo ürün", "basit ürün");

        when(productRepository.findById(1)).thenReturn(Optional.of(demoProduct));
        when(productRepository.save(any())).thenReturn(new Product(1, "demo4", 4000, "demo ürün güncellendi", "basit ürün 4"));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/{id}", 1)
                        .content(convertObjectAsString(demoProduct))
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("demo4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("demo ürün güncellendi"));
    }

    @Test
    public void deleteProductByIdTest() throws Exception {
        //Bu kod, Mockito ile productRepository'nin deleteById metodunu bir int parametresiyle çağırdığında, herhangi bir işlem yapmadan (exception atmadan) geçmesini sağlar.
        Mockito.doNothing().when(productRepository).deleteById(anyInt());
        when(productRepository.count()).thenReturn(Long.valueOf(100));

        //andDo(print()), Spring testlerinde, gerçekleştirilen HTTP isteği ve yanıtının konsola yazdırılmasını sağlayan bir metottur.
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", 12)
                ).andDo(print()).
                andExpect(status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.").value(100));
    }

    private String convertObjectAsString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}