package com.hrv.mart.apigatewayadmin.controller

import com.hrv.mart.apigatewayadmin.model.AuthResponse
import com.hrv.mart.apigatewayadmin.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.security.Principal

@RestController
@RequestMapping("/user")
class UserController(
    private val authService: AuthService
)
{
    @GetMapping
    fun getMyself(principal: Principal): Mono<ResponseEntity<AuthResponse>> {
        return Mono.justOrEmpty(authService.getUser(principal.name))
            .map { ResponseEntity.ok(AuthResponse(it.email)) }
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()))
    }
}