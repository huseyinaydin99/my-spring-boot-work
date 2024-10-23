package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.CustomerDto;
import tr.com.huseyinaydin.entity.Customer;
import tr.com.huseyinaydin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public List<Customer> saveCustomers(@RequestBody List<Customer> customers) {
        return service.addNewCustomers(customers);
    }

    @GetMapping
    public List<CustomerDto> fetchAllCustomers() {
        return service.getCustomers();
    }
}