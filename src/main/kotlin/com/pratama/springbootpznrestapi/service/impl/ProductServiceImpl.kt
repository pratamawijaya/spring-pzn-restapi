package com.pratama.springbootpznrestapi.service.impl

import com.pratama.springbootpznrestapi.entity.Product
import com.pratama.springbootpznrestapi.error.ProductNotFoundException
import com.pratama.springbootpznrestapi.model.ProductResponse
import com.pratama.springbootpznrestapi.model.request.CreateProductRequest
import com.pratama.springbootpznrestapi.model.request.UpdateProductRequest
import com.pratama.springbootpznrestapi.repository.ProductRepository
import com.pratama.springbootpznrestapi.service.ProductService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(val productRepository: ProductRepository) : ProductService {
    val logger: Logger = LoggerFactory.getLogger(ProductServiceImpl::class.java)

    override fun get(): List<ProductResponse> {
        val products = productRepository.findAll()
        val listProductResponse = mutableListOf<ProductResponse>()
        logger.info("list product : ${products.size}")
        products.map {
            listProductResponse.add(mapProductToProductResponse(it))
        }
        logger.info("list product after mapping : ${listProductResponse.size}")
        return listProductResponse
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
        val product = productRepository.findByIdOrNull(id)
        if (product != null) {
            return mapProductToProductResponse(product)
        } else {
            throw ProductNotFoundException()
        }
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

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {

        val product = productRepository.findByIdOrNull(id)
        logger.info("update request : $updateProductRequest")

        if (product != null) {
            product.apply {
                name = updateProductRequest.name ?: ""
                price = updateProductRequest.price
                quantity = updateProductRequest.quantity
                updatedAt = Date()
            }
            logger.info("product updated $product")
            productRepository.save(product)
        } else {
            throw ProductNotFoundException()
        }

        return mapProductToProductResponse(product)
    }
}
