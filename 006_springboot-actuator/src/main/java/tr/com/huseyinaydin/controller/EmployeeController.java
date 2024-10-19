package tr.com.huseyinaydin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.dto.Employee;
import tr.com.huseyinaydin.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    //@GetMapping("/health")
    //public HttpStatus getHealth(){
    //    return HttpStatus.OK;
    //}

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() throws JsonProcessingException {
        List<Employee> employees = service.getEmployees();
        log.info("EmployeeController:getAllEmployees tüm çalışanlar çekildi {}", new ObjectMapper().writeValueAsString(employees));
        return employees;
    }
}