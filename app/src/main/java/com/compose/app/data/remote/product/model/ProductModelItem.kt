package com.compose.app.data.remote.product.model


import com.compose.app.data.remote.category.model.CategoryModelItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductModelItem(
    @Json(name = "category")
    val category: CategoryModelItem,
    @Json(name = "creationAt")
    val creationAt: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "images")
    val images: List<String>,
    @Json(name = "price")
    val price: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "updatedAt")
    val updatedAt: String
)