package tr.com.huseyinaydin.repository;

import tr.com.huseyinaydin.entity.Employee;

import java.util.List;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

public interface EmployeeRepository {

    int save(Employee employee);

    List<Employee> findAll();

    List<Employee> getALlEmployees();

    Employee findById(int id);

    String getNameById(int id);

    List<Employee> findByNameAndDept(String name, String dept);

    int update(Employee employee);

    int delete(int id);
}