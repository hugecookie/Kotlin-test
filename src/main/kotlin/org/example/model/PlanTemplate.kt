package org.example.model

data class PlanTemplate(
    val id: Int,
    val name: String,
    val recommendedSpaceType: SpaceType,
    val recommendedProductIds: List<Int>
)
