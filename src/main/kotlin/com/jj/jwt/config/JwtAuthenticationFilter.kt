package com.jj.jwt.config

import com.jj.jwt.service.JwtTokenProvider
import com.jj.jwt.service.UserService
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
        private val userService: UserService
): OncePerRequestFilter () {

//    private val logger = KotlinLogging.logger {}

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token: String? = request.getHeader(HttpHeaders.AUTHORIZATION)

        println (">>>>>> " + token)

//        token?: throw AuthException("Token not exists")

        if (token != null ) {

            val issuer = JwtTokenProvider.validateAndGetIssuer(token)

            println ("ISSUER :::" + issuer)
//            if (issuer == null) return

            if (issuer != null) {
                logger.info("   issuer {$issuer}")
                val user = userService.findUser(issuer)

                SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(user, "", null)

            }

        }





        filterChain.doFilter(request, response)
    }
}