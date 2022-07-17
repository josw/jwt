package com.jj.jwt.service

import com.jj.jwt.model.User
import com.jj.jwt.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository
) {

    fun save(user: User): User {
        return this.userRepository.save(user)
    }

    fun findByEmail(email: String): User? {
        return this.userRepository.findByEmail(email)
    }

    fun findUser(id: Int): User? {
        return this.userRepository.findByIdOrNull(id)
    }

}