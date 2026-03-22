package org.example.batch

data class ProductBulkProcessingResult(
    val requestedCount: Int,
    val createdCount: Int,
    val updatedCount: Int,
    val skippedCount: Int,
    val chunkCount: Int
)
