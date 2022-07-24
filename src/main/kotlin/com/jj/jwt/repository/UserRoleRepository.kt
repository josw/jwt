package com.jj.jwt.repository

import com.jj.jwt.model.UserRole
import org.springframework.data.jpa.repository.JpaRepository

interface UserRoleRepository: JpaRepository<UserRole, Int>{

}