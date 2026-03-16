package org.example.service

import org.example.model.LayoutPlan
import org.example.model.PlanStatus
import org.example.model.PlacedProduct
import org.example.model.Space
import org.example.model.User
import org.example.repository.InMemoryLayoutPlanRepository

class SpacePlanningService(
    private val layoutPlanRepository: InMemoryLayoutPlanRepository,
    private val productCatalogService: ProductCatalogService
) {
    fun createPlan(
        id: Int,
        title: String,
        designer: User,
        customerName: String,
        space: Space
    ): LayoutPlan {
        require(layoutPlanRepository.findById(id) == null) { "이미 존재하는 설계안입니다: $id" }
        return layoutPlanRepository.save(
            LayoutPlan(
                id = id,
                title = title,
                designer = designer,
                customerName = customerName,
                space = space
            )
        )
    }

    fun addProductToPlan(
        planId: Int,
        placementId: Int,
        productId: Int,
        xMm: Int,
        yMm: Int,
        rotationDegrees: Int,
        quantity: Int = 1
    ): LayoutPlan {
        val plan = getPlan(planId)
        require(plan.placedProducts.none { it.placementId == placementId }) { "이미 존재하는 배치 ID입니다: $placementId" }

        val product = productCatalogService.getProductById(productId)
        validatePlacement(plan.space.widthMm, plan.space.depthMm, xMm, yMm, product.widthMm, product.depthMm)

        val placement = PlacedProduct(
            placementId = placementId,
            product = product,
            xMm = xMm,
            yMm = yMm,
            rotationDegrees = rotationDegrees,
            quantity = quantity
        )

        return layoutPlanRepository.save(plan.copy(placedProducts = plan.placedProducts + placement))
    }

    fun addNote(planId: Int, note: String): LayoutPlan {
        val plan = getPlan(planId)
        return layoutPlanRepository.save(plan.copy(notes = plan.notes + note))
    }

    fun reviewPlan(planId: Int): LayoutPlan {
        val plan = getPlan(planId)
        require(plan.placedProducts.isNotEmpty()) { "검토할 배치 제품이 없습니다." }
        return layoutPlanRepository.save(plan.copy(status = PlanStatus.REVIEWED))
    }

    fun markOrdered(planId: Int): LayoutPlan {
        val plan = getPlan(planId)
        return layoutPlanRepository.save(plan.copy(status = PlanStatus.ORDERED))
    }

    fun getPlan(planId: Int): LayoutPlan {
        return layoutPlanRepository.findById(planId)
            ?: throw NoSuchElementException("설계안을 찾을 수 없습니다: $planId")
    }

    fun getAllPlans(): List<LayoutPlan> = layoutPlanRepository.findAll()

    private fun validatePlacement(
        spaceWidthMm: Int,
        spaceDepthMm: Int,
        xMm: Int,
        yMm: Int,
        productWidthMm: Int,
        productDepthMm: Int
    ) {
        require(xMm >= 0 && yMm >= 0) { "배치 좌표는 0 이상이어야 합니다." }
        require(xMm + productWidthMm <= spaceWidthMm) { "제품이 공간 너비를 초과합니다." }
        require(yMm + productDepthMm <= spaceDepthMm) { "제품이 공간 깊이를 초과합니다." }
    }
}
