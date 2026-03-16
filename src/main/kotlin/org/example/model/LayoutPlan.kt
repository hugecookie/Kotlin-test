package org.example.model

data class LayoutPlan(
    val id: Int,
    val title: String,
    val designer: User,
    val customerName: String,
    val space: Space,
    val placedProducts: List<PlacedProduct> = emptyList(),
    val notes: List<String> = emptyList(),
    val status: PlanStatus = PlanStatus.DRAFT
)
