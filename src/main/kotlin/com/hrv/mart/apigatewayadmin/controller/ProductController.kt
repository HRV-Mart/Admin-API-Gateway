package com.hrv.mart.apigatewayadmin.controller

import com.hrv.mart.apigatewayadmin.service.ProductService
import com.hrv.mart.product.Product
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController("/admin/product")
class ProductController (
    private val productService: ProductService
)
{
    @PostMapping
    fun createProduct(
        @RequestBody product: Product,
        principal: Principal
    ) =
        productService.createProduct(product)
    @PutMapping
    fun updateProduct(
        @RequestBody product: Product,
        principal: Principal
    ) =
        productService.updateProduct(product)
    @DeleteMapping("/{productId}")
    fun deleteMapping(@PathVariable productId: String) =
        productService.deleteProduct(productId)
}