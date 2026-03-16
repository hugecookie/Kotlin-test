package org.example.service

import org.example.model.Product
import org.example.model.ProductCategory
import org.example.repository.InMemoryProductRepository

class ProductCatalogService(
    private val productRepository: InMemoryProductRepository
) {
    fun registerProduct(product: Product): Product {
        require(productRepository.findById(product.id) == null) { "이미 존재하는 제품입니다: ${product.id}" }
        return productRepository.save(product)
    }

    fun updateProduct(product: Product): Product {
        require(productRepository.findById(product.id) != null) { "수정할 제품이 없습니다: ${product.id}" }
        return productRepository.save(product)
    }

    fun getProductById(id: Int): Product {
        return productRepository.findById(id)
            ?: throw NoSuchElementException("제품을 찾을 수 없습니다: $id")
    }

    fun getProductsByCategory(category: ProductCategory): List<Product> {
        return productRepository.findAll().filter { it.category == category }
    }

    fun getAllProducts(): List<Product> = productRepository.findAll()
}
