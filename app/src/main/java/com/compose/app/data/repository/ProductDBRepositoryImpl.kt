package com.compose.app.data.repository

import com.compose.app.data.local.CartDao
import com.compose.app.data.remote.product.model.product.ProductModelItem
import com.compose.app.domain.repository.ProductDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductDBRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : ProductDBRepository {
    override suspend fun insertProduct(product: ProductModelItem) {
        try {
            val quantity = getProductQuantity(product.id)
            if (quantity == null) {
                cartDao.insertProduct(product)
            } else {
                updateProductQuantity(quantity + 1, product.id)
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun deleteProduct(product: ProductModelItem) = cartDao.deleteProduct(product)
    override suspend fun deleteAllProducts() = cartDao.deleteAllProducts()

    override suspend fun updateProductQuantity(productQuantity: Int, productId: Int) =
        cartDao.updateProductQuantity(productQuantity, productId)

    override fun getAllProducts(): Flow<List<ProductModelItem>> = cartDao.getAllProducts()

    private suspend fun getProductQuantity(productId: Int): Int? =
        cartDao.getProductQuantity(productId)
}