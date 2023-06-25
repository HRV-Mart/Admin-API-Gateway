package com.hrv.mart.apigatewayadmin.service

import com.hrv.mart.product.model.Product
import com.hrv.mart.product.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class ProductService(
    @Autowired
    private val productRepository: ProductRepository
) {
    fun createProduct (
        product: Product,
        response: ServerHttpResponse
    ) =
        productRepository
            .createProduct(
                product,
                response
            )
    fun updateProduct(
        product: Product,
        response: ServerHttpResponse
    ) =
        productRepository
            .updateProduct(
                product,
                response
            )
    fun deleteProduct(
        productId: String,
        response: ServerHttpResponse
    ) =
        productRepository
            .deleteProduct(
                productId,
                response
            )
}
