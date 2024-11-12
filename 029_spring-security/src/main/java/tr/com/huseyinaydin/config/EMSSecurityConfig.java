//package tr.com.huseyinaydin.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

//بسم الله الرحمن الرحيم

/**
 *
 * @author Huseyin_Aydin
 * @since 1994
 * @category Java, Spring Boot
 *
 */

//@Configuration
//@EnableWebSecurity
//public class EMSSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("huseyinaydin99")
//                .password(passwordEncoder().encode("t00r"))
//                .roles("USER");
//
//        auth.inMemoryAuthentication()
//                .withUser("ceyda99")
//                .password(passwordEncoder().encode("t00r2"))
//                .roles("ADMIN");
//
//        auth.inMemoryAuthentication()
//                .withUser("kullanici")
//                .password(passwordEncoder().encode("kullanici"))
//                .roles("USER", "ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/nonsecureapp").permitAll()
//                .and()
//                .authorizeRequests().antMatchers("/welcome", "/text")
//                .authenticated().and().httpBasic();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}