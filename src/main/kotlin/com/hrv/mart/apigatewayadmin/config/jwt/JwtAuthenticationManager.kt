package com.hrv.mart.apigatewayadmin.config.jwt

import com.hrv.mart.apigatewayadmin.service.jwt.JWTService
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JwtAuthenticationManager(private val jwtSigner: JWTService) : ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        return Mono.just(authentication)
            .map { jwtSigner.validateJwt(it.credentials as String) }
            .onErrorResume {
                Mono.empty()
            }
            .map { jws ->
                UsernamePasswordAuthenticationToken(
                    jws.body.subject,
                    authentication.credentials as String,
                        mutableListOf(SimpleGrantedAuthority(jws.header["role"].toString()))
                )
            }
    }
}