package org.example.service

import org.example.model.Order
import org.example.repository.InMemoryOrderRepository

class OrderService(
    private val orderRepository: InMemoryOrderRepository,
    private val spacePlanningService: SpacePlanningService,
    private val bomService: BomService
) {
    fun createOrder(orderId: Int, planId: Int): Order {
        val plan = spacePlanningService.getPlan(planId)
        require(plan.status.name == "REVIEWED") { "검토 완료된 설계안만 주문할 수 있습니다." }

        val bom = bomService.generateBom(planId)
        val order = Order(
            id = orderId,
            planId = planId,
            customerName = plan.customerName,
            items = bom.items,
            totalPrice = bom.totalPrice
        )

        orderRepository.save(order)
        spacePlanningService.markOrdered(planId)
        return order
    }

    fun getAllOrders(): List<Order> = orderRepository.findAll()
}
