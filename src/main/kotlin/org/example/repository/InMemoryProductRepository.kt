package org.example.repository

import org.example.model.Product

class InMemoryProductRepository {
    private val products = mutableListOf<Product>()

    fun save(product: Product): Product {
        val index = products.indexOfFirst { it.id == product.id }
        if (index >= 0) {
            products[index] = product
        } else {
            products.add(product)
        }
        return product
    }

    fun findById(id: Int): Product? = products.find { it.id == id }

    fun findAll(): List<Product> = products.toList()
}
