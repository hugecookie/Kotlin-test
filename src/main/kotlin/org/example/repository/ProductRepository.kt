package org.example.repository

import org.example.model.Product

interface ProductRepository {
    fun save(product: Product): Product
    fun saveAll(products: List<Product>): List<Product>
    fun findById(id: Int): Product?
    fun findAll(): List<Product>
}
