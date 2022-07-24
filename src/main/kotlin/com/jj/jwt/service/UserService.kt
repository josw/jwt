package com.jj.jwt.service

import com.jj.jwt.model.User
import com.jj.jwt.model.UserRole
import com.jj.jwt.repository.UserRepository
import com.jj.jwt.repository.UserRoleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository,
        private val userRoleRepository: UserRoleRepository

) {

    fun save(user: User): User {

        val savedUser = userRepository.save(user)

        userRoleRepository.save(UserRole(
                userId = user.id,
                role = "ROLE_READ"
        ))

        return savedUser
    }

    fun findByEmail(email: String): User? {
        return this.userRepository.findByEmail(email)
    }

    fun findUser(id: Int): User? {
        return this.userRepository.findByIdOrNull(id)
    }

}