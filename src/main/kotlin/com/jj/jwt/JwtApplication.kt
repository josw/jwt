package com.jj.jwt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class JwtApplication

fun main(args: Array<String>) {
    runApplication<JwtApplication>(*args)
}
