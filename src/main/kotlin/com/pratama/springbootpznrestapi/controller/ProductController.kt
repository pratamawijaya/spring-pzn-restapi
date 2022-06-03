package com.pratama.springbootpznrestapi.controller

import com.pratama.springbootpznrestapi.model.ProductResponse
import com.pratama.springbootpznrestapi.model.WebResponse
import com.pratama.springbootpznrestapi.model.request.CreateProductRequest
import com.pratama.springbootpznrestapi.service.ProductService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class ProductController(val service: ProductService) {

    @PostMapping(
        value = ["/api/products"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProduct(@Valid @RequestBody body: CreateProductRequest): WebResponse<ProductResponse> {
        val response = service.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }
}