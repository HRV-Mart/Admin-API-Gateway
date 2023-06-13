package com.hrv.mart.apigatewayadmin.controller

import com.hrv.mart.apigatewayadmin.model.AuthRequest
import com.hrv.mart.apigatewayadmin.service.jwt.JWTService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
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
    @PostMapping("/signup")
    fun signup() =
        jwtService
            .validateJwt("eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhIiwiaXNzIjoiaWRlbnRpdHkiLCJleHAiOjE2ODY2NDY2MTEsImlhdCI6MTY4NjY0NTcxMX0.bwxSIIg7cyocTThyKo38DFw8cgV7-FTRHVUHfqN0TbySdss4RP-SEy94aQF4uydTrEwaBisXZl4SqMSXqVglGJhwtakcHVxVkaHRpiH-PLbktmkNlGvQRxaZ7_Yqevu2s9bIE4buSeX3o2CjsIuED5jF164JHWEXMsMxLHjKG_R3NRFAhtB8t-OS7w5T7hPfoZGIEm7QEMeuSjoTDdgYeh_43yCFo2ql8dZcItc2LkiaoDu2-b6xuGpaNp651RYTmozZzu8QxV3gH_THjttUpECIgVAB6oosHQF78Gzqkq0sPxNrpGmH4SDb5aP-j126pvVQK1mAScbG0xCb-J_zGQ")
            .toMono()
}