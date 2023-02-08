package com.jj.jwt.controller

import com.jj.jwt.dto.LoginDTO
import com.jj.jwt.dto.RegisterDTO
import com.jj.jwt.model.Message
import com.jj.jwt.model.User
import com.jj.jwt.service.JwtTokenProvider
import com.jj.jwt.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

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
        val user = userService.findByEmail(loginDTO.email)
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


    @GetMapping("/read")
    @Secured("ROLE_READ")
    fun read(): ResponseEntity<Any> {
        return ResponseEntity.ok("READ")
    }


    @GetMapping("/error")
    @Secured("ROLE_ERROR")
    fun error(): ResponseEntity<Any> {
        return ResponseEntity.ok("error!")
    }

}
