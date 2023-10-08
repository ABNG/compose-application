package com.compose.app.data.remote.product


import com.compose.app.data.remote.product.model.category.CategoryModelItem
import com.compose.app.data.remote.product.model.product.ProductModelItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApiService {
    @GET("products")
    suspend fun getAllProducts(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): List<ProductModelItem>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") productId: Int): ProductModelItem

    @GET("categories")
    suspend fun getAllCategories(@Query("limit") limit: Int): List<CategoryModelItem>

    @GET("categories/{id}/products")
    suspend fun getAllProductsByCategoryId(
        @Path("id") categoryId: Int, @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): List<ProductModelItem>
}