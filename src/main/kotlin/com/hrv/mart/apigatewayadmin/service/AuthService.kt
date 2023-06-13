package com.hrv.mart.apigatewayadmin.service

import com.hrv.mart.apigatewayadmin.model.AuthRequest
import org.springframework.stereotype.Service

@Service
class AuthService {
    private val users = mutableMapOf<String, AuthRequest>()
    fun addUser(authRequest: AuthRequest) {
        users[authRequest.email] = authRequest
    }
    fun getUser(email: String) =
        users[email]
    fun login(authRequest: AuthRequest) =
        if (users.containsKey(authRequest.email)) {
            users[authRequest.email]!!.password == authRequest.password
        } else {
            false
        }
}