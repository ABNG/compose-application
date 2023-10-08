package com.compose.app.data.repository

import com.compose.app.data.remote.product.ProductApiService
import com.compose.app.data.remote.product.model.category.CategoryModelItem
import com.compose.app.data.remote.product.model.product.ProductModelItem
import com.compose.app.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApiService: ProductApiService
) : ProductRepository {
    override suspend fun getAllProducts(offset: Int, limit: Int): List<ProductModelItem> =
        productApiService.getAllProducts(offset, limit)

    override suspend fun getProductById(productId: Int): ProductModelItem =
        productApiService.getProductById(productId)

    override suspend fun getAllCategories(limit: Int): List<CategoryModelItem> =
        productApiService.getAllCategories(limit)

    override suspend fun getAllProductsByCategoryId(
        categoryId: Int, offset: Int,
        limit: Int
    ): List<ProductModelItem> =
        productApiService.getAllProductsByCategoryId(categoryId, offset, limit)
}