package com.hrv.mart.apigatewayadmin.controller

import com.hrv.mart.apigatewayadmin.service.ProductService
import com.hrv.mart.product.model.Product
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/admin/product")
class ProductController (
    private val productService: ProductService
)
{
    @PostMapping
    fun createProduct(
        @RequestBody product: Product,
        principal: Principal,
        response: ServerHttpResponse
    ) =
        productService.createProduct(
            product,
            response
        )
    @PutMapping
    fun updateProduct(
        @RequestBody product: Product,
        principal: Principal,
        response: ServerHttpResponse
    ) =
        productService.updateProduct(product, response)
    @DeleteMapping("/{productId}")
    fun deleteMapping(
        @PathVariable productId: String,
        response: ServerHttpResponse
    ) =
        productService.deleteProduct(productId, response)
}
