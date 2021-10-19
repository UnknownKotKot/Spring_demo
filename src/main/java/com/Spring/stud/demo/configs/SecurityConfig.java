package com.Spring.stud.demo.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Authentication Manager");
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/profile/**").authenticated()
                .antMatchers("/api/v1/admin/**").hasAnyRole("ADMIN", "SUPERADMIN")
                .antMatchers("/api/v1/users/me").authenticated()
                .antMatchers("/api/v1/users/**").hasAnyRole("ADMIN", "SUPERADMIN")
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/auth_page/**").authenticated()
                .antMatchers("/user_info").authenticated()
//                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN") // ROLE_ADMIN, ROLE_SUPERADMIN
                .antMatchers("/test/a/**").hasAnyAuthority("A")
                .antMatchers("/test/b/**").hasAnyAuthority("B")
                .anyRequest().permitAll()
                .and()
//                .formLogin()
//                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
//                .logout()
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID");
//                .and()
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(true);
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
