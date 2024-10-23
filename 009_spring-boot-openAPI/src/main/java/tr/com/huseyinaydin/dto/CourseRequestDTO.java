package tr.com.huseyinaydin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import tr.com.huseyinaydin.annotation.CourseTypeValidation;

import javax.validation.constraints.*;
import java.util.Date;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {

    @NotBlank(message = "Kurs adı boş veya null olmamalı.")
    private String name;

    @NotEmpty(message = "Eğitmen kısmı girilmelidir.")
    private String trainerName;

    @NotNull(message = "Sürenin belirtilmesi gereklidir.")
    private String duration; // günler...

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Past(message = "Başlangıç zamanı şu an ki zamandan geçmişte olamaz.")
    private Date startDate;

    @CourseTypeValidation
    private String courseType; //canlı yayın veya önceden kayıt.

    @Min(value = 1500, message = "Kurs fiyatı 1500'den az olamaz.")
    @Max(value = 5000, message = "Kurs fiyatı 5000'den fazla olamaz.")
    private double fees;

    private boolean isCertificateAvailable;

    @NotEmpty(message = "Açıklamanın belirtilmesi gerekmektedir.")
    @Length(min = 5,max = 10)
    private String description;

    @Email(message = "Geçersiz e-posta Id.")
    private String emailId;

    @Pattern(regexp = "^[0-9]{10}$")
    private String contact;
}