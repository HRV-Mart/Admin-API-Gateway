package com.hrv.mart.apigatewayadmin.service

class JWTEnhancer {

    companion object {
        private const val specialCharCode= 34
        fun enhanceJWT(jwt: String) =
            jwt.replace(specialCharCode.toChar().toString(), "")
    }
}