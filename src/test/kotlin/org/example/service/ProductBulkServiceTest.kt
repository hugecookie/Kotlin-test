package org.example.service

import kotlin.test.Test
import kotlin.test.assertEquals
import org.example.batch.ProductBulkCommand
import org.example.model.Product
import org.example.model.ProductCategory
import org.example.repository.InMemoryProductRepository

class ProductBulkServiceTest {
    private val repository = InMemoryProductRepository()
    private val service = ProductBulkService(repository)

    @Test
    fun `청크 단위로 신규 상품을 적재한다`() {
        val result = service.upsertInChunks(
            commands = (1..2_500).asSequence().map { id ->
                ProductBulkCommand(product = product(id))
            },
            chunkSize = 1_000
        )

        assertEquals(2_500, result.requestedCount)
        assertEquals(2_500, result.createdCount)
        assertEquals(0, result.updatedCount)
        assertEquals(0, result.skippedCount)
        assertEquals(3, result.chunkCount)
        assertEquals(2_500, repository.findAll().size)
    }

    @Test
    fun `기존 상품은 overwrite 옵션이 있을 때만 수정한다`() {
        repository.save(product(1, name = "original"))

        val result = service.upsertInChunks(
            commands = sequenceOf(
                ProductBulkCommand(product = product(1, name = "skip-update")),
                ProductBulkCommand(product = product(1, name = "apply-update"), overwriteExisting = true)
            ),
            chunkSize = 1
        )

        assertEquals(2, result.requestedCount)
        assertEquals(0, result.createdCount)
        assertEquals(1, result.updatedCount)
        assertEquals(1, result.skippedCount)
        assertEquals("apply-update", repository.findById(1)?.name)
    }

    private fun product(id: Int, name: String = "product-$id"): Product {
        return Product(
            id = id,
            name = name,
            category = ProductCategory.STORAGE_CABINET,
            widthMm = 600,
            depthMm = 600,
            heightMm = 2100,
            price = 100_000
        )
    }
}
