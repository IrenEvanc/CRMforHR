package com.company.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static javax.management.Query.and;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //теперь внедряем свой провайдер
    @Autowired
    private CustomAuthencationProvider customAuthencationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").hasAnyRole( "employeIt","employeeHr","managerHr")
                .and().formLogin()
                .permitAll()
                .loginPage("/login")
                .loginProcessingUrl("/perform-login")
                .usernameParameter("user")
                .passwordParameter("pass")
                .defaultSuccessUrl("/menu")
                .and().rememberMe();
    }



    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //и добавляем его сюда
        auth.authenticationProvider(customAuthencationProvider);
    }


}
