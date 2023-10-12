package com.compose.app.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.compose.app.data.remote.product.model.product.ProductModelItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductModelItem)

    @Delete
    suspend fun deleteProduct(product: ProductModelItem)

    @Query("DELETE FROM Product")
    suspend fun deleteAllProducts()

    @Query("UPDATE Product SET quantity = :productQuantity WHERE id= :productId")
    suspend fun updateProductQuantity(productQuantity: Int, productId: Int)

    @Query("SELECT * from Product")
    fun getAllProducts(): Flow<List<ProductModelItem>>

    //get quantity if product exist
    @Query("SELECT quantity from Product WHERE id = :productId")
    suspend fun getProductQuantity(productId: Int): Int?
}