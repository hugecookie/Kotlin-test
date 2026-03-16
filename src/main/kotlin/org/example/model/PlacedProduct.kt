package org.example.model

data class PlacedProduct(
    val placementId: Int,
    val product: Product,
    val xMm: Int,
    val yMm: Int,
    val rotationDegrees: Int,
    val quantity: Int = 1
)
