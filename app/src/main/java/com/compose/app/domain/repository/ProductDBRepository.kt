package com.compose.app.domain.repository

import com.compose.app.data.remote.product.model.product.ProductModelItem
import kotlinx.coroutines.flow.Flow

interface ProductDBRepository {
    suspend fun insertProduct(product: ProductModelItem)
    suspend fun deleteProduct(product: ProductModelItem)
    suspend fun deleteAllProducts()
    suspend fun updateProductQuantity(productQuantity: Int, productId: Int)
    fun getAllProducts(): Flow<List<ProductModelItem>>
}