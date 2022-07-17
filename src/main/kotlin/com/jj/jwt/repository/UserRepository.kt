package com.jj.jwt.repository

import com.jj.jwt.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {

    fun findByEmail(email: String): User?
}