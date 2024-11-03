package tr.com.huseyinaydin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

//RDMBS yapıdaki veritabanında tabloların MongoDB'deki karşılığı Collection'dur.
@Document(collection = "Tasks")   //@Entity   @Table(name="")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private String taskId;

    private String description;
    private String priority; //p1 ,p2 , p3
    private String assignee;
    private int storyPoint; //5
}