package com.jj.jwt.config

import com.jj.jwt.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
//@Configuration
class SecurityConfig(
        private val userService: UserService
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers("/api/login", "/api/register").permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .addFilterBefore(JwtAuthenticationFilter(userService), UsernamePasswordAuthenticationFilter::class.java)



        return http.build()
    }


}