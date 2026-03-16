package org.example.model

data class BomItem(
    val productId: Int,
    val productName: String,
    val quantity: Int,
    val unitPrice: Int
) {
    val totalPrice: Int
        get() = quantity * unitPrice
}
