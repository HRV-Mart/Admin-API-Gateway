package com.hrv.mart.apigatewayadmin.config.security

import com.hrv.mart.authlibrary.model.UserType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter

@Configuration
class SecurityConfig {
    @Bean
    fun applyHttpFilter(
        http: ServerHttpSecurity,
        jwtAuthenticationManager: ReactiveAuthenticationManager,
        jwtAuthenticationConverter: ServerAuthenticationConverter
    ): SecurityWebFilterChain {
        val authenticationWebFilter = AuthenticationWebFilter(jwtAuthenticationManager)
        authenticationWebFilter.setServerAuthenticationConverter(jwtAuthenticationConverter)

        return http
            .authorizeExchange {
                it
                    .pathMatchers("/auth/login")
                    .permitAll()
                    .pathMatchers("/auth/signup")
                    .permitAll()
                    .pathMatchers("/admin/**")
                    .hasAuthority(UserType.ADMIN.name)
            }
            .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .httpBasic {
                it.disable()
            }
            .csrf {
                it.disable()
            }
            .logout {
                it.disable()
            }
            .build()
    }
}