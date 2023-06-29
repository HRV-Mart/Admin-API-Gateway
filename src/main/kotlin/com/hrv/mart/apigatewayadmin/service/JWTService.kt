package com.hrv.mart.apigatewayadmin.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.SecureRandom
import java.time.Duration
import java.time.Instant
import java.util.*

@Service
class JWTService (
    @Value("\${hrv.mart.jwt.secret}")
    private val secret: String
)
{
    val keyPair = generateKeyFromSecret()

    private fun generateKeyFromSecret(): KeyPair {
        val secretBytes = secret.toByteArray()

        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")

        val secureRandom = SecureRandom.getInstance("SHA1PRNG")
        secureRandom.setSeed(secretBytes)
        keyPairGenerator.initialize(2048, secureRandom)

        return keyPairGenerator.generateKeyPair()
    }

    fun createJwt(userId: String): String =
        Jwts.builder()
            .signWith(keyPair.private, SignatureAlgorithm.RS256)
            .setSubject(userId)
            .setIssuer("identity")
            .setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(15))))
            .setIssuedAt(Date.from(Instant.now()))
            .compact()

    /**
     * Validate the JWT where it will throw an exception if it isn't valid.
     */
    fun validateJwt(jwt: String) =
        Jwts.parserBuilder()
            .setSigningKey(keyPair.public)
            .build()
            .parseClaimsJws(JWTEnhancer.enhanceJWT(jwt))

}