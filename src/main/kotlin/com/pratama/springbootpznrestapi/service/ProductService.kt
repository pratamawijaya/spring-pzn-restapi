package com.pratama.springbootpznrestapi.service

import com.pratama.springbootpznrestapi.model.ProductResponse
import com.pratama.springbootpznrestapi.model.request.CreateProductRequest

interface ProductService {
    fun create(createProductRequest: CreateProductRequest): ProductResponse
}