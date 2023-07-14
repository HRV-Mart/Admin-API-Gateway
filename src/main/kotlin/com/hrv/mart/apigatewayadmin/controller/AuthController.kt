package com.hrv.mart.apigatewayadmin.controller

import com.hrv.mart.apigatewayadmin.service.AuthService
import com.hrv.mart.apigatewayadmin.service.jwt.JWTService
import com.hrv.mart.authlibrary.model.UserType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController (
    @Autowired
    private val jwtService: JWTService,
    private val authService: AuthService
)
{
    @PostMapping("/login")
    fun login(@RequestBody appwriteJWT: String, response: ServerHttpResponse) =
        authService
            .login(appwriteJWT, response)
            .map {
                jwtService
                    .createJwt(
                        userId = it.email,
                        role = UserType.ADMIN
                    )
            }
}