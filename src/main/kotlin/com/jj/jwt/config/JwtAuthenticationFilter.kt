package com.jj.jwt.config

import com.jj.jwt.exception.AuthException
import com.jj.jwt.service.JwtTokenProvider
import com.jj.jwt.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
        private val userService: UserService
): OncePerRequestFilter() {
//    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
//
//        val token: String? = (request as HttpServletRequest).getHeader("Authorization")
//
//        println (">>>>>> " + token)
//
//        token?: throw AuthException("Token not exists")
//
//        val issuer = JwtTokenProvider.validateAndGetIssuer(token)
//
//        val user = userService.findUser(issuer)
//
//        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(user, "", null)
//
//        chain.doFilter(request, response)
//    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token: String? = (request as HttpServletRequest).getHeader(HttpHeaders.AUTHORIZATION)

        println (">>>>>> " + token)

        token?: throw AuthException("Token not exists")

        val issuer = JwtTokenProvider.validateAndGetIssuer(token)

        if (issuer == null) return

        val user = userService.findUser(issuer)

        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(user, "", null)



        filterChain.doFilter(request, response)
    }
}