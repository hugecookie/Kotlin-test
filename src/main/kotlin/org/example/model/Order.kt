package org.example.model

data class Order(
    val id: Int,
    val planId: Int,
    val customerName: String,
    val items: List<BomItem>,
    val totalPrice: Int,
    val status: OrderStatus = OrderStatus.CREATED
)
