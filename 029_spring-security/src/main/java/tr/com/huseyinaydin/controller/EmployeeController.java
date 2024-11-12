package tr.com.huseyinaydin.controller;

import tr.com.huseyinaydin.dto.AuthRequest;
import tr.com.huseyinaydin.entity.Employee;
import tr.com.huseyinaydin.service.EmployeeService;
import tr.com.huseyinaydin.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
@RequestMapping("/employees")
public class EmployeeController {

    public static final String AUTHORITY_ROLE_HR = "hasAuthority('ROLE_HR')";

    @Autowired
    private EmployeeService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Hoşgeldin kardeşim benim. Burası bir karpuz tarlası :). https://www.youtube.com/watch?v=IZiZGYez4nA";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUserName());
        } else {
            throw new UsernameNotFoundException("Böyle bir kullanıcı sistemde kayıtlı değil.");
        }
    }

    @PostMapping("/create")
    //@PreAuthorize(AUTHORITY_ROLE_HR)
    public Employee onboardNewEmployee(@RequestBody Employee employee) {
        return service.createNewEmployee(employee);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_HR') or hasAuthority('ROLE_MANAGER')")
    public List<Employee> getAll() {
        return service.getEmployees();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return service.getEmployee(id);
    }

    @PutMapping("/update")
    @PreAuthorize(AUTHORITY_ROLE_HR)
    public Employee updateRoles(@RequestBody Employee employee) {
        return service.changeRoleOfEmployee(employee);
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public String test(Principal principal) {
        return principal.getName();
    }
}