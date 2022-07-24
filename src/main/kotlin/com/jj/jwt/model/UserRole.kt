package com.jj.jwt.model

import javax.persistence.*

@Entity
@Table(
    indexes = [ Index(name="user_id_idx", columnList = "userId", unique = false) ]
)
data class UserRole (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0,

    @Column
    var userId: Int,

    @Column
    var role: String
)