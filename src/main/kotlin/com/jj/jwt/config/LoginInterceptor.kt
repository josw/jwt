package com.jj.jwt.config

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginInterceptor: HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {


        val auth = SecurityContextHolder.getContext().authentication

        println ("AUTHED ::::: " + auth.isAuthenticated)


        return true
    }


}