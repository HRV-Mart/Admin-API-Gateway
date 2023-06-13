package com.hrv.mart.apigatewayadmin.controller

import com.hrv.mart.apigatewayadmin.model.AuthRequest
import com.hrv.mart.apigatewayadmin.service.jwt.JWTService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.kotlin.core.publisher.toMono

@RestController
@RequestMapping("/auth")
class AuthController (
    @Autowired
    private val jwtService: JWTService
)
{
    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest) =
        jwtService
            .createJwt(authRequest.email)
            .toMono()

}