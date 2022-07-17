package com.jj.jwt.config

import com.jj.jwt.dto.ErrorMessageDTO
import com.jj.jwt.exception.AuthException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
internal class ExceptionControllerAdvice {

//
//    @ExceptionHandler(AuthException::class)
//    fun handleIllegalStateException(ex: AuthException): ResponseEntity<ErrorMessageDTO> {
//
//        val errorMessage = ErrorMessageDTO(
//                HttpStatus.NOT_FOUND.value(),
//                ex.message
//        )
//        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
//    }
}