package com.hrv.mart.apigatewayadmin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
class SecurityConfig {
    @Bean
    fun applyHttpFilter(http: ServerHttpSecurity): SecurityWebFilterChain =
        http
            .authorizeExchange {
                it
                    .pathMatchers("/auth/login")
                    .permitAll()
                    .pathMatchers("/auth/signup")
                    .permitAll()
                    .pathMatchers("/user")
                    .authenticated()
            }
            .httpBasic {
                it.disable()
            }
            .csrf {
                it.disable()
            }
            .logout{
                it.disable()
            }
            .build()
}