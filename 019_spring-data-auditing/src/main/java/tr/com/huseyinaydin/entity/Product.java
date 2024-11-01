package tr.com.huseyinaydin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "PRODUCT_TBL")

//@EntityListeners(AuditingEntityListener.class) anotasyonu, Spring Boot'ta Auditing işlemlerini etkinleştirmek için kullanılır. Bu anotasyonu ekleyerek, veri tabanında bir kaydın ne zaman oluşturulduğunu ve kim tarafından güncellendiğini otomatik olarak izleyebilirim. AuditingEntityListener, nesneye özel değişiklikleri algılayarak ilgili alanları güncellememe yardımcı olur.
@EntityListeners(AuditingEntityListener.class)
@Audited
//@Audited anotasyonu, Hibernate Envers kütüphanesi ile birlikte kullanılarak bir varlık sınıfındaki tüm değişikliklerin geçmişini otomatik olarak tutmamı sağlar. Bu anotasyonu eklediğimde, veri tabanında her güncelleme ve silme işlemi ayrı bir revizyon kaydı olarak saklanır. Bu sayede, verilerin önceki versiyonlarına kolayca ulaşabilir ve değişim geçmişini inceleyebilirim.
/*
Revizyon, Hibernate Envers’te audit işlemleri için her bir değişiklik kaydına verilen benzersiz bir kimliktir. Veri tabanındaki bir varlıkta değişiklik (ekleme, güncelleme, silme) yapıldığında, Envers bu işlemi bir "revizyon" olarak tanımlar ve bu değişikliğin tarihini, kim tarafından yapıldığını ve yapılan işlem detaylarını revizyon numarası ile _AUD tablosunda saklar. Böylece, revizyon numaraları ile geçmiş değişiklikler izlenebilir ve belirli bir kaydın eski versiyonlarına geri dönmek veya değişikliklerin tarihçesini görmek mümkün olur.
*/
/*
@Audited anotasyonu kullanıldığında, her bir değişiklik Hibernate Envers tarafından otomatik olarak veri tabanında oluşturulan *_AUD tablolarında saklanır. Örneğin, User adında bir varlık sınıfını audit'lemek için @Audited anotasyonunu eklediğimizde, Hibernate Envers otomatik olarak User_AUD adında bir audit tablosu oluşturur. Bu tabloda her revizyon için bir kayıt bulunur ve her bir revizyon, revizyon numarası, tarih ve yapılan işlemlerle ilgili ek bilgilerle saklanır.
*/
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;
    //@Column(name = "DESC")
    private String description;
    private String productType;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date createdDate; // DATE, TIME, BOTH(TimeStamp), her ikiside.

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
}