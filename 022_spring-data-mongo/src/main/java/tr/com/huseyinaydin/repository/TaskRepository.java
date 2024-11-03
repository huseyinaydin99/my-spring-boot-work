package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByAssigneeAndPriority(String assignee, String priority);

/*
    Bu sorgu, MongoDB'de belirli bir assignee ve priority değerine sahip görevleri bulmak için kullanılır.
    ?0 ve ?1, sırasıyla bu iki parametreyi temsil eder.
    Sorgunun sonuçları yalnızca description ve storyPoint alanlarını içerecek şekilde sınırlandırılmıştır,
    böylece daha az veri iletilerek performans artırılır.
 */
    @Query(value = "{assignee: ?0 ,priority: ?1}", fields = "{'description' : 1 , 'storyPoint': 2}")
    List<Task> findTaskWithAssigneeAndPriority(String assignee, String priority);
/*
MongoDB'de sorgu sonuçlarını kısıtlarken kullanılan fields parametresindeki 1 ve 2 değerleri, ilgili alanların nasıl döneceğini belirler:

1: Bu, ilgili alanın sorgu sonuçlarında yer alacağını belirtir. Yani, {'description' : 1} ifadesi, description alanının sonuçta gösterilmesini istiyoruz demektir.
0: Eğer 0 kullanılsaydı, ilgili alanın sonuçta yer almayacağını belirtirdi.
2: Ancak, burada storyPoint için 2 kullanımı yanlıştır. MongoDB'de bu şekilde alan gösterimi yoktur. 1 kullanılması yeterlidir; bu yüzden {'storyPoint': 1} olmalıdır.
 */


    //operator (IN/LIKE/BETWEEN)
    //sayfalama ve sıralama
}