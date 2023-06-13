package com.hrv.mart.apigatewayadmin.controller

import com.hrv.mart.apigatewayadmin.model.AuthRequest
import com.hrv.mart.apigatewayadmin.service.jwt.AuthService
import com.hrv.mart.apigatewayadmin.service.jwt.JWTService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@RestController
@RequestMapping("/auth")
class AuthController (
    @Autowired
    private val jwtService: JWTService,
    private val authService: AuthService
)
{
    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest, response: ServerHttpResponse) =
        authService
            .login(authRequest)
            .toMono()
            .map {
                if (it) {
                    jwtService.createJwt(authRequest.email)
                }
                else {
                    response.statusCode = HttpStatus.UNAUTHORIZED
                    ""
                }
            }
    @PostMapping("/signup")
    fun signup(@RequestBody authRequest: AuthRequest) =
        authService
            .addUser(authRequest)
            .toMono()
            .then(Mono.just("Sign up successful"))
}