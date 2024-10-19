package tr.com.huseyinaydin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tr.com.huseyinaydin.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

@Service
@Slf4j
public class EmployeeService {

    public List<Employee> getEmployees() throws JsonProcessingException {
        List<Employee> employees = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> new Employee(i, "çalışan " + i)).collect(Collectors.toList());
        log.info("EmployeeService:getEmployees sistemden tüm çalışanlar bulundu.  {}", new ObjectMapper().writeValueAsString(employees));
        return employees;
    }
}