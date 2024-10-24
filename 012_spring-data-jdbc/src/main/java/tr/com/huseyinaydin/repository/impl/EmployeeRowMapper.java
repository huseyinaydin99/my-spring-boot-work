package tr.com.huseyinaydin.repository.impl;

import tr.com.huseyinaydin.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//بسم الله الرحمن الرحيم

/**
 * @author Huseyin_Aydin
 * @category Java, Spring Boot
 * @since 1994
 */

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Employee.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .dept(rs.getString("dept"))
                .email(rs.getString("email"))
                .doj(rs.getDate("doj"))
                .build();
    }
}