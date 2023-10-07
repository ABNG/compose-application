package com.compose.app.data.remote.category

import com.compose.app.data.remote.category.model.CategoryModel
import com.compose.app.data.remote.product.model.ProductModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryApiService {

    @GET("categories")
    suspend fun getAllCategories() : CategoryModel

    @GET("categories/{id}/products")
    suspend fun getAllProductsByCategoryId(@Path("id") categoryId: Int) : ProductModel
}