package com.hrv.mart.apigatewayadmin.config.repository

import com.hrv.mart.authlibrary.repository.AuthRepository
import com.hrv.mart.product.repository.ProductRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class RepositoryConfig (
    @Value("\${hrv.mart.authUrl}")
    private val authServiceURL: String,
    @Value("\${hrv.mart.productUrl}")
    private val productUrl: String
)
{
    @Bean
    fun getAuthRepository() =
        AuthRepository(authServiceURL)
    @Bean
    fun getProductRepository() =
        ProductRepository(WebClient.builder(), productUrl)
}