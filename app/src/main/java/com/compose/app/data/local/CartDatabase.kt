package com.compose.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.compose.app.data.remote.product.model.product.ProductModelItem

@Database(entities = [ProductModelItem::class], version = 1, exportSchema = false)
@TypeConverters(value = [Converters::class])
abstract class CartDatabase : RoomDatabase() {
    abstract val cartDao: CartDao
}