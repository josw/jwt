package com.jj.jwt.controller

import com.jj.jwt.dto.ErrorMessageDTO
import com.jj.jwt.dto.LoginDTO
import com.jj.jwt.dto.RegisterDTO
import com.jj.jwt.exception.AuthException
import com.jj.jwt.model.Message
import com.jj.jwt.model.User
import com.jj.jwt.service.JwtTokenProvider
import com.jj.jwt.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api")
class AuthController(
        private val userService: UserService
) {
    @PostMapping("/register")
    fun register(@RequestBody registerDTO: RegisterDTO): ResponseEntity<User> {

        val user = User()
        user.name = registerDTO.name
        user.email = registerDTO.email
        user.password = registerDTO.password

        return ResponseEntity.ok(this.userService.save(user))
    }

    @PostMapping("login")
    fun login(@RequestBody loginDTO: LoginDTO ): ResponseEntity<Any> {
        var user = userService.findByEmail(loginDTO.email)
                ?: return ResponseEntity.badRequest().body(Message("user not found"))

        if (!user.comparePassword(loginDTO.password)) {
            return ResponseEntity.badRequest().body(Message("invalid password"))
        }

        return ResponseEntity.ok(JwtTokenProvider.createToken(user))
    }

    @GetMapping("/hello")
    fun hello(): ResponseEntity<Any> {
        return ResponseEntity.ok("Hello")
    }



}
