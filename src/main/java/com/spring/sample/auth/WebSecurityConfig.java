package com.spring.sample.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/img/**",
                "/css/**",
                "/js/**",
                "/bootstrap/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 認可の設定
        http.authorizeRequests()
                .antMatchers("/login", "/login").permitAll()
                .antMatchers("/sign_up", "/sign_up").permitAll()
                .anyRequest().authenticated();

        // ログイン設定
        http.formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .failureHandler(new ToDoAuthenticationFailureHandler());
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}
