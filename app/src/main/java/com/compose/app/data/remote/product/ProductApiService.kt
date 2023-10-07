package com.compose.app.data.remote.product

import com.compose.app.data.remote.product.model.ProductModel
import com.compose.app.data.remote.product.model.ProductModelItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApiService {
    @GET("products")
    suspend fun getAllProducts(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ProductModel

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") productId: Int): ProductModelItem
}