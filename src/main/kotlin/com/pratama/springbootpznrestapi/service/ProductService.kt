package com.pratama.springbootpznrestapi.service

import com.pratama.springbootpznrestapi.model.ProductResponse
import com.pratama.springbootpznrestapi.model.request.CreateProductRequest
import com.pratama.springbootpznrestapi.model.request.UpdateProductRequest

interface ProductService {
    fun create(createProductRequest: CreateProductRequest): ProductResponse

    fun get(id:String): ProductResponse

    fun get():List<ProductResponse>

    fun update(id:String, updateProductRequest: UpdateProductRequest):ProductResponse
}