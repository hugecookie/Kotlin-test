package org.example.service

import org.example.model.PlanTemplate
import org.example.model.SpaceType
import org.example.repository.InMemoryTemplateRepository

class AdminCatalogService(
    private val templateRepository: InMemoryTemplateRepository,
    private val productCatalogService: ProductCatalogService
) {
    fun registerTemplate(
        id: Int,
        name: String,
        recommendedSpaceType: SpaceType,
        recommendedProductIds: List<Int>
    ): PlanTemplate {
        require(recommendedProductIds.isNotEmpty()) { "추천 제품이 하나 이상 필요합니다." }
        recommendedProductIds.forEach { productCatalogService.getProductById(it) }

        return templateRepository.save(
            PlanTemplate(
                id = id,
                name = name,
                recommendedSpaceType = recommendedSpaceType,
                recommendedProductIds = recommendedProductIds
            )
        )
    }

    fun getAllTemplates(): List<PlanTemplate> = templateRepository.findAll()
}
