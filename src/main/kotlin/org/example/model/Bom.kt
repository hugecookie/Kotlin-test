package org.example.model

data class Bom(
    val planId: Int,
    val items: List<BomItem>
) {
    val totalPrice: Int
        get() = items.sumOf { it.totalPrice }
}
