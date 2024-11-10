package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@RepositoryRestResource(collectionResourceRel = "ogrenci-api", path = "ogrenci-api")
public interface StudentRepository extends JpaRepository<Student, Integer> {

     List<Student> findBySection(String section);
}

/*
@RepositoryRestResource annotasyonu, Spring Data REST kullanarak veri tabanındaki verileri otomatik olarak bir
REST API'ye dönüştürmek için kullanılır. Bu annotasyon, bir JPA repository'sini doğrudan bir RESTful web
servisine bağlar, böylece CRUD işlemleri (Create, Read, Update, Delete) için otomatik endpoint'ler oluşturur.
collectionResourceRel parametresi, API'deki koleksiyon kaynağının adını belirtir, bu ad genellikle JSON
yanıtında yer alır. path parametresi ise, bu API'nin erişileceği URL yolunu belirler. Örneğin,
path = "ogrenci-api" kullanıldığında, API'ye http://localhost:8080/ogrenci-api yoluyla erişilebilir.
*/