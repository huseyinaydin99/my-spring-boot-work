package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.Employee;
import tr.com.huseyinaydin.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public String saveEmployee(Employee employee) {
        int count = repository.save(employee);
        return "Kayıt girildi. " + count;
    }

    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    public List<Employee> getEmployeesUsingBeanPropertyRowMapper() {
        return repository.getALlEmployees();
    }

    public Employee getEmployeeById(int id) {
        return repository.findById(id);
    }

    public String getNameById(int id) {
        return repository.getNameById(id);
    }

    public List<Employee> findEmployeesByNameAndDept(String name, String dept) {
        return repository.findByNameAndDept(name, dept);
    }

    public String updateEmployee(Employee employee) {
        int count = repository.update(employee);
        return count + " Kayıt güncellendi.";
    }

    public String deleteEmployee(int id) {
        int count = repository.delete(id);
        return count + " Kayıt silindi.";
    }
}