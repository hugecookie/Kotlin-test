package org.example.service

import org.example.model.Bom
import org.example.model.BomItem

class BomService(
    private val spacePlanningService: SpacePlanningService
) {
    fun generateBom(planId: Int): Bom {
        val plan = spacePlanningService.getPlan(planId)
        val items = plan.placedProducts
            .groupBy { it.product.id }
            .values
            .map { placements ->
                val product = placements.first().product
                BomItem(
                    productId = product.id,
                    productName = product.name,
                    quantity = placements.sumOf { it.quantity },
                    unitPrice = product.price
                )
            }
            .sortedBy { it.productId }

        return Bom(planId = plan.id, items = items)
    }
}
