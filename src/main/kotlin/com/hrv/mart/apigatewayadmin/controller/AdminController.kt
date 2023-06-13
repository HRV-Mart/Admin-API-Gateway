package com.hrv.mart.apigatewayadmin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.security.Principal

@RestController
@RequestMapping("/admin")
class AdminController {
    @GetMapping
    fun admin(principal: Principal) =
        Mono.just("Hello Admin ${principal.name}")
}