package com.hrv.mart.apigatewayadmin.service

import com.hrv.mart.authlibrary.repository.AuthRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Service

@Service
class AuthService (
    @Autowired
    private val authRepository: AuthRepository
)
{
    private val specialCharCode= 34
    fun login(appwriteJWT: String, response: ServerHttpResponse) =
        authRepository
            .createAdminAuth(
                jwt = appwriteJWT.replace(specialCharCode.toChar().toString(), ""),
                response = response
            )
}