package tr.com.huseyinaydin.service;

import tr.com.huseyinaydin.entity.Employee;
import tr.com.huseyinaydin.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Service
public class EmployeeUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = repository.findByUserName(username);
        return employee.map(EmployeeUserDetails::new)
                        .orElseThrow(() -> new UsernameNotFoundException(username + " kullanıcı adlı kişi sistemde bulunamadı!"));
    }
}