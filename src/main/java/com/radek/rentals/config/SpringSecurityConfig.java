package com.radek.rentals.config;

import com.radek.rentals.AuthEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthEntryPoint authEntryPoint;

    @Autowired
    public SpringSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder,
                                AuthEntryPoint authEntryPoint) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authEntryPoint = authEntryPoint;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .authorizeRequests()
                .antMatchers("/users/**").hasAnyRole("ADMIN")
                .antMatchers("/rentals/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .logout().permitAll();


//        http.authorizeRequests()
//                .antMatchers("/home").permitAll()
//                .antMatchers("/admin/**").hasAnyRole("ADMIN")
//                .anyRequest().authenticated();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(bCryptPasswordEncoder.encode("pass")).roles("ADMIN")
                .and()
                .withUser("user").password(bCryptPasswordEncoder.encode("pass")).roles("USER")
                .and()
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
