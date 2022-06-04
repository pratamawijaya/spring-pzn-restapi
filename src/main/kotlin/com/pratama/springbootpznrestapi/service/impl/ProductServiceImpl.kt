package com.pratama.springbootpznrestapi.service.impl

import com.pratama.springbootpznrestapi.entity.Product
import com.pratama.springbootpznrestapi.error.ProductNotFoundException
import com.pratama.springbootpznrestapi.model.ProductResponse
import com.pratama.springbootpznrestapi.model.request.CreateProductRequest
import com.pratama.springbootpznrestapi.model.request.ListProductRequest
import com.pratama.springbootpznrestapi.model.request.UpdateProductRequest
import com.pratama.springbootpznrestapi.repository.ProductRepository
import com.pratama.springbootpznrestapi.service.ProductService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.HashMap
import kotlin.math.log

@Service
class ProductServiceImpl(val productRepository: ProductRepository) : ProductService {
    val logger: Logger = LoggerFactory.getLogger(ProductServiceImpl::class.java)

    override fun list(productRequest: ListProductRequest): HashMap<String, Any> {
        val pageable = PageRequest.of(productRequest.page, productRequest.size)
        val page = productRepository.findAll(pageable)
        val products: List<Product> = page.content

        val response = HashMap<String, Any>()
        response["items"] = products
        response["currentPage"] = page.number
        response["totalItems"] = page.totalElements
        response["totalPages"] = page.totalPages

        return response
    }

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        val product = Product(
            id = createProductRequest.id,
            name = createProductRequest.name,
            price = createProductRequest.price,
            quantity = createProductRequest.quantity,
            createdAt = Date(),
            updatedAt = null
        )
        productRepository.save(product)
        return mapProductToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product = findProductByIdOrThrow(id)
        return mapProductToProductResponse(product)
    }


    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        val product = findProductByIdOrThrow(id)
        logger.info("update request : $updateProductRequest")

        product.apply {
            name = updateProductRequest.name ?: ""
            price = updateProductRequest.price
            quantity = updateProductRequest.quantity
            updatedAt = Date()
        }
        logger.info("product updated $product")
        productRepository.save(product)


        return mapProductToProductResponse(product)
    }

    override fun delete(id: String) {
        val product = findProductByIdOrThrow(id)
        productRepository.deleteById(product.id)
    }

    private fun mapProductToProductResponse(product: Product): ProductResponse {
        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }

    private fun findProductByIdOrThrow(id: String): Product {
        val product = productRepository.findByIdOrNull(id)
        if (product == null) {
            throw ProductNotFoundException()
        } else {
            return product
        }
    }
}
