package com.hrv.mart.apigatewayadmin.config.jwt

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.*

@Component
class JwtServerAuthenticationConverter : ServerAuthenticationConverter {
    override fun convert(exchange: ServerWebExchange?): Mono<Authentication> {
        return justOrEmpty(exchange)
            .flatMap { justOrEmpty(it.request.headers["X-Auth"]) }
            .filter { it.isNotEmpty() }
            .map { it[0] }
            .map { UsernamePasswordAuthenticationToken(it, it) }
    }
}