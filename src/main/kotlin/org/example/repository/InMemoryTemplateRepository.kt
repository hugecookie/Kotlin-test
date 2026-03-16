package org.example.repository

import org.example.model.PlanTemplate

class InMemoryTemplateRepository {
    private val templates = mutableListOf<PlanTemplate>()

    fun save(template: PlanTemplate): PlanTemplate {
        val index = templates.indexOfFirst { it.id == template.id }
        if (index >= 0) {
            templates[index] = template
        } else {
            templates.add(template)
        }
        return template
    }

    fun findAll(): List<PlanTemplate> = templates.toList()
}
