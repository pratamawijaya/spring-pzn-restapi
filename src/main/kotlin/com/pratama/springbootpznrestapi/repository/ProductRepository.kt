package com.pratama.springbootpznrestapi.repository

import com.pratama.springbootpznrestapi.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String> {
}