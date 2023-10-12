package com.compose.app.data.remote.product.model.product


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.compose.app.data.remote.product.model.category.CategoryModelItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "Product")
@JsonClass(generateAdapter = true)
data class ProductModelItem(
    @Embedded(prefix = "category_")
    @Json(name = "category")
    val category: CategoryModelItem,
    @Json(name = "description")
    val description: String,
    @PrimaryKey
    @Json(name = "id")
    val id: Int,
    @TypeConverters
    @Json(name = "images")
    val images: List<String>,
    @Json(name = "price")
    val price: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "quantity")
    val quantity: Int = 1
)