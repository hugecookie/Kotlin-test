package org.example.batch

import org.example.model.Product

data class ProductBulkCommand(
    val product: Product,
    val overwriteExisting: Boolean = false
)
