package com.jj.jwt.service

import com.jj.jwt.exception.AuthException
import com.jj.jwt.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtTokenProvider {

    companion object {

        private val SECRET = "secret"

        fun createToken(user: User): String {

            val issuer = user.id.toString()

            return Jwts.builder()
                    .setIssuer(issuer)
                    .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000))
                    .signWith(SignatureAlgorithm.HS256, SECRET).compact()
        }


        fun validateAndGetIssuer(token: String): Int {
            return try {
                val claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token)
                if (claims.body.expiration.before(Date())) {
                    claims.body.issuer.toInt()
                } else {
                    throw AuthException("Token expired")
                }
            } catch (e: Exception) {
                println (">>>> ERROR Parsing token " + e.message + "::::" + token)
                throw AuthException("Auth error ")
            }
        }

    }



}