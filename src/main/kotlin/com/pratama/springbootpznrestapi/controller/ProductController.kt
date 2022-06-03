package com.pratama.springbootpznrestapi.controller

import com.pratama.springbootpznrestapi.model.ProductResponse
import com.pratama.springbootpznrestapi.model.WebResponse
import com.pratama.springbootpznrestapi.model.request.CreateProductRequest
import com.pratama.springbootpznrestapi.model.request.UpdateProductRequest
import com.pratama.springbootpznrestapi.service.ProductService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class ProductController(val service: ProductService) {

    @GetMapping(
        value = ["/api/products"],
        produces = ["application/json"]
    )
    fun getAllProduct(): WebResponse<List<ProductResponse>> {
        val response = service.get()
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

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

    @GetMapping(
        value = ["/api/products/{id}"],
        produces = ["application/json"]
    )
    fun getProduct(@PathVariable("id") id: String): WebResponse<ProductResponse> {
        val productResponse = service.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @PutMapping(
        value = ["/api/products/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateProduct(
        @PathVariable("id") id: String,
        @Valid @RequestBody body: UpdateProductRequest
    ): WebResponse<ProductResponse> {
        val productResponse = service.update(id, body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = productResponse
        )
    }

    @DeleteMapping(
        value = ["/api/products/{id}"],
        produces = ["application/json"]
    )
    fun deleteProduct(@PathVariable("id") id: String): WebResponse<String> {
        service.delete(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = id
        )
    }
}