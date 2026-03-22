package org.example.service

import org.example.batch.ProductBulkCommand
import org.example.batch.ProductBulkProcessingResult
import org.example.model.Product
import org.example.repository.ProductRepository

class ProductBulkService(
    private val productRepository: ProductRepository
) {
    fun upsertInChunks(
        commands: Sequence<ProductBulkCommand>,
        chunkSize: Int = DEFAULT_CHUNK_SIZE
    ): ProductBulkProcessingResult {
        require(chunkSize > 0) { "chunkSize는 1 이상이어야 합니다." }

        var requestedCount = 0
        var createdCount = 0
        var updatedCount = 0
        var skippedCount = 0
        var chunkCount = 0

        commands.chunked(chunkSize).forEach { chunk ->
            val productsToSave = mutableListOf<Product>()

            chunk.forEach { command ->
                requestedCount++
                val existing = productRepository.findById(command.product.id)

                when {
                    existing == null -> {
                        productsToSave.add(command.product)
                        createdCount++
                    }

                    command.overwriteExisting -> {
                        productsToSave.add(command.product)
                        updatedCount++
                    }

                    else -> skippedCount++
                }
            }

            if (productsToSave.isNotEmpty()) {
                productRepository.saveAll(productsToSave)
            }

            chunkCount++
        }

        return ProductBulkProcessingResult(
            requestedCount = requestedCount,
            createdCount = createdCount,
            updatedCount = updatedCount,
            skippedCount = skippedCount,
            chunkCount = chunkCount
        )
    }

    private fun <T> Sequence<T>.chunked(size: Int): Sequence<List<T>> = sequence {
        val iterator = iterator()

        while (iterator.hasNext()) {
            val chunk = ArrayList<T>(size)
            repeat(size) {
                if (iterator.hasNext()) {
                    chunk.add(iterator.next())
                }
            }
            if (chunk.isNotEmpty()) {
                yield(chunk)
            }
        }
    }

    companion object {
        const val DEFAULT_CHUNK_SIZE: Int = 1_000
    }
}
