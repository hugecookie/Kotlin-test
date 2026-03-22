package org.example.repository

import org.example.model.Product

class InMemoryProductRepository : ProductRepository {
    private val products = linkedMapOf<Int, Product>()

    override fun save(product: Product): Product {
        products[product.id] = product
        return product
    }

    override fun saveAll(products: List<Product>): List<Product> {
        products.forEach { product -> this.products[product.id] = product }
        return products
    }

    override fun findById(id: Int): Product? = products[id]

    override fun findAll(): List<Product> = products.values.toList()
}
