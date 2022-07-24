package com.jj.jwt.model

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*


@Entity
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var name = ""

    @Column(unique = true)
    var email = ""

    @Column
    var password = ""
        get() = field
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="userId")
    var userRoles: List<UserRole> = mutableListOf()

    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }

}