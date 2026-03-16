package org.example.repository

import org.example.model.LayoutPlan

class InMemoryLayoutPlanRepository {
    private val plans = mutableListOf<LayoutPlan>()

    fun save(plan: LayoutPlan): LayoutPlan {
        val index = plans.indexOfFirst { it.id == plan.id }
        if (index >= 0) {
            plans[index] = plan
        } else {
            plans.add(plan)
        }
        return plan
    }

    fun findById(id: Int): LayoutPlan? = plans.find { it.id == id }

    fun findAll(): List<LayoutPlan> = plans.toList()
}
