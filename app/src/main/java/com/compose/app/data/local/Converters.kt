package com.compose.app.data.local

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun toImage(images: List<String>): String {
        return images[0]
    }

    @TypeConverter
    fun fromImage(image: String): List<String> {
        return listOf(image)
    }
}