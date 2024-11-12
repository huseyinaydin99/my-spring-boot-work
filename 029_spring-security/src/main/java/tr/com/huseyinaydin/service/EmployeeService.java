package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.Employee;
import tr.com.huseyinaydin.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Service
public class EmployeeService {

    public static final String DEFAULT_ROLE = "ROLE_EMPLOYEE";

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Employee createNewEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRoles(DEFAULT_ROLE);
        return repository.save(employee);
    }

    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    public Employee getEmployee(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + " Id'li kullanıcı bulunamadı!"));
    }

    public Employee changeRoleOfEmployee(Employee employee){
        Employee existingEmployee = getEmployee(employee.getId());
        existingEmployee.setRoles(employee.getRoles());
        return repository.save(existingEmployee);
    }
}