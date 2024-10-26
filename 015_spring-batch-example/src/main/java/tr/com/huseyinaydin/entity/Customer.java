package tr.com.huseyinaydin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMERS_DETAILS")
public class Customer {

    @Id
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String contactNo;
    private String country;
    private String dob;
}