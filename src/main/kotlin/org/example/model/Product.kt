package org.example.model

data class Product(
    val id: Int,
    val name: String,
    val category: ProductCategory,
    val widthMm: Int,
    val depthMm: Int,
    val heightMm: Int,
    val price: Int
)
