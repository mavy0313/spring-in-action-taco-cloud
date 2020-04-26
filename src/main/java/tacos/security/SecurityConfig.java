package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder encoder() {
    return new StandardPasswordEncoder("53cr3t");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(encoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
          .antMatchers("/design", "/orders")
            .hasRole("ROLE_USER")
          .antMatchers("/", "/**")
            .permitAll();
  }

//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http
//        .authorizeRequests()
//          .antMatchers("/design", "/orders")
//            .access("hasRole('ROLE_USER') && " +
//              "T(java.util.Calendar).getInstance().get("+
//              "T(java.util.Calendar).DAY_OF_WEEK) == " +
//              "T(java.util.Calendar).TUESDAY")
//          .antMatchers("/", "/**")
//            .access("permitAll");
//  }

//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth
//      .inMemoryAuthentication()
//        .withUser("buzz")
//          .password("infinity")
//          .authorities("ROLE_USER")
//        .and()
//        .withUser("woody")
//          .password("bullseye")
//          .authorities("ROLE_USER");
//  }
}