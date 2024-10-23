package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.dto.CustomerDto;
import tr.com.huseyinaydin.entity.Customer;
import tr.com.huseyinaydin.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Service
@Profile(value = {"dev", "stg", "prod"})
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Value("${application.message}")
    private String message;

    @PostConstruct
    public void init() {
        System.out.println("##**##**##**## " + message);
    }

    public List<Customer> addNewCustomers(List<Customer> customers) {
        return repository.saveAll(customers);
    }

    public List<CustomerDto> getCustomers() {
        List<Customer> customerList = repository.findAll();
        return customerList.stream().map(customer ->
                        new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone(),
                                getDateFormat(customer.getDob())))
                .collect(Collectors.toList());
    }

    private String getDateFormat(Date date) {
        return new SimpleDateFormat("MM/dd/yyyy").format(date);
    }
}