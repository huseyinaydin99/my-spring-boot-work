package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.hash.Customer;
import tr.com.huseyinaydin.repository.CustomerDAO;
import tr.com.huseyinaydin.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerDAO dao;

    private static final String CACHE_NAME = "customers";

    @Autowired
    private CustomerRepository repository;

    @CachePut(key = "#customer.id", value = CACHE_NAME)
    public Customer saveCustomer(Customer customer) {
        //return dao.addCustomer(customer);
        log.info("CustomerService::saveCustomer() kayıt veritabanına yerleştirildi");
        return repository.save(customer);
    }

    @Cacheable(value = CACHE_NAME)
    public List<Customer> getAllCustomers() {
        //return dao.getAllCustomers();
        log.info("CustomerService::getAllCustomers() veritabanından tüm veriler çekildi");
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Cacheable(key = "#id", value = CACHE_NAME)
    public Customer getCustomer(int id) {
        //return dao.getCustomer(id);
        log.info("CustomerService::getCustomer() veritabanından sadece id'ye göre müşteri kaydı çekildi");
        return repository.findById(id).get();
    }

    @CacheEvict(key = "#id", value = CACHE_NAME)
    public String deleteCustomer(int id) {
        //return dao.deleteCustomer(id);
        log.info("CustomerService::deleteCustomer() id'de göre müşteri kaydını sil");
        repository.deleteById(id);
        return "müşteri silindi !";
    }

    @CachePut(key = "#id", value = CACHE_NAME)
    public Customer updateCustomer(int id, Customer customer) {
        //return dao.updateCustomer(id, customer);
        log.info("CustomerService::updateCustomer() kayıt veritabanından silindi");
        Customer existingCustomer = repository.findById(id).get();
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setDob(customer.getDob());
        return repository.save(existingCustomer);
    }
}