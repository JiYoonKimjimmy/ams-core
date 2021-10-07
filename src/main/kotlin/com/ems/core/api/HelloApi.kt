package com.ems.core.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/hello")
class HelloApi {

    @GetMapping("/")
    fun hello(): String {
        return "Hello world2"
    }

}