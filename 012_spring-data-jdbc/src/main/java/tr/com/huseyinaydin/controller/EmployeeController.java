package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.entity.Employee;
import tr.com.huseyinaydin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping
    public String saveEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return service.getEmployees();
    }

    @GetMapping("/2nd")
    public List<Employee> getEmployeesBy2ndApproach() {
        return service.getEmployeesUsingBeanPropertyRowMapper();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return service.getEmployeeById(id);
    }

    @GetMapping("/name/{id}")
    public String getNameById(@PathVariable int id) {
        return service.getNameById(id);
    }

    @GetMapping("/{name}/{dept}")
    public List<Employee> findEmployeesByNameAndDept(@PathVariable String name, @PathVariable String dept) {
        return service.findEmployeesByNameAndDept(name, dept);
    }

    @PutMapping
    public String updateEmployee(@RequestBody Employee employee) {
        return service.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        return service.deleteEmployee(id);
    }
}