package com.compose.app.data.remote.category.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryModelItem(
    @Json(name = "creationAt")
    val creationAt: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "updatedAt")
    val updatedAt: String
)