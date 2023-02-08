package com.jj.jwt.service

import com.jj.jwt.dto.JwtResponse
import com.jj.jwt.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtTokenProvider {

    companion object {

        private val SECRET = "secret"
        private val log = KotlinLogging.logger {}

        fun createToken(user: User): JwtResponse {

            val issuer = user.id.toString()
            val claims = Jwts.claims().setSubject(issuer)
            val roles = user.userRoles.map { it.role }

            claims["roles"] = roles

            log.info(" ROLES :::{}", roles)

            return JwtResponse(Jwts.builder()
                    .setClaims(claims)
                    .setIssuer(issuer)
                    .setExpiration(Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                    .signWith(SignatureAlgorithm.HS256, SECRET).compact()
                , null);
        }


        fun validateAndGetIssuer(token: String): Int? {
            return try {
                val claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token)
                if (claims.body.expiration.before(Date())) {
                    return null
                }
                claims.body.issuer.toInt()
            } catch (e: Exception) {
                println (">>>> ERROR Parsing token " + e.message + "::::" + token)
                null
            }
        }

    }
}
