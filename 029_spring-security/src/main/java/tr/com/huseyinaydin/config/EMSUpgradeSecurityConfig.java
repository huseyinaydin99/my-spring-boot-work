package tr.com.huseyinaydin.config;

import tr.com.huseyinaydin.filter.JwtAuthFilter;
import tr.com.huseyinaydin.service.EmployeeUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
/*
@EnableGlobalMethodSecurity(prePostEnabled = true) anotasyonu, Spring Security'de yöntem düzeyinde güvenlik
sağlamak için kullanılır. prePostEnabled = true parametresi, @PreAuthorize ve @PostAuthorize anotasyonlarının
etkinleştirilmesini sağlar, böylece belirli yöntemlerin çalıştırılmadan önce veya sonra yetkilendirme
kontrolleri yapılabilir. Bu sayede, kodda hassas verilere erişimi sınırlandırarak güvenliği artırabiliriz.
*/
public class EMSUpgradeSecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    //authentication and rolling - kimlik doğrulama ve rollendirme
    @Bean
    public UserDetailsService userDetailsService() {
//        UserDetails employee = User.withUsername("huseyinaydin99")
//                .password(passwordEncoder.encode("toor"))
//                .roles("EMPLOYEE").build();
//
//        UserDetails hr = User.withUsername("hamit")
//                .password(passwordEncoder.encode("hamido"))
//                .roles("HR").build();
//
//        UserDetails admin = User.withUsername("bugra99")
//                .password(passwordEncoder.encode("toor"))
//                .roles("MANAGER", "HR").build();
//
//        return new InMemoryUserDetailsManager(employee, admin, hr);
        return new EmployeeUserDetailsService();
    }

    //authorization - yetkilendirme
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.authorizeRequests()
//                .antMatchers("/nonsecureapp").permitAll()
//                .and()
//                .authorizeRequests().antMatchers("/welcome", "/text")
//                .authenticated().and().httpBasic().and().build();
        return http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/employees/welcome", "/employees/create", "/employees/authenticate").permitAll()
                .and()
                .authorizeRequests().antMatchers("/employees/**")
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}