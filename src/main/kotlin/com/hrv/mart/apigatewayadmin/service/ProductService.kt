package com.hrv.mart.apigatewayadmin.service

import com.hrv.mart.product.Product
import com.hrv.mart.product.repository.ProductRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class ProductService(
    @Value("\${hrv.mart.productUrl}")
    private val productUrl: String
) {
    private val productRepository =
        ProductRepository(
            WebClient.builder(),
            productUrl
        )
    fun createProduct (product: Product) =
        productRepository
            .createProduct(product)
    fun updateProduct(product: Product) =
        productRepository
            .updateProduct(product)
    fun deleteProduct(productId: String) =
        productRepository
            .deleteProduct(productId)
}