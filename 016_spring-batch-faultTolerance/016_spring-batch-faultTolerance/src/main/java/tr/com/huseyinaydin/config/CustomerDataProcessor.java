package tr.com.huseyinaydin.config;

import tr.com.huseyinaydin.entity.Customer;
import org.springframework.batch.item.ItemProcessor;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

public class CustomerDataProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {
        int age = Integer.parseInt(customer.getAge());
        if (age >= 18) {
            return customer;
        }
        return null;
    }
}