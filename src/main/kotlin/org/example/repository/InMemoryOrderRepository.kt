package org.example.repository

import org.example.model.Order

class InMemoryOrderRepository {
    private val orders = mutableListOf<Order>()

    fun save(order: Order): Order {
        val index = orders.indexOfFirst { it.id == order.id }
        if (index >= 0) {
            orders[index] = order
        } else {
            orders.add(order)
        }
        return order
    }

    fun findAll(): List<Order> = orders.toList()
}
