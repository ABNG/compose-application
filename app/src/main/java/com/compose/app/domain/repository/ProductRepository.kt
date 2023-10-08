package com.compose.app.domain.repository


import com.compose.app.data.remote.product.model.category.CategoryModelItem
import com.compose.app.data.remote.product.model.product.ProductModelItem

interface ProductRepository {

    suspend fun getAllProducts(
        offset: Int,
        limit: Int
    ): List<ProductModelItem>

    suspend fun getProductById(productId: Int): ProductModelItem

    suspend fun getAllCategories(limit: Int): List<CategoryModelItem>

    suspend fun getAllProductsByCategoryId(
        categoryId: Int, offset: Int,
        limit: Int
    ): List<ProductModelItem>
}