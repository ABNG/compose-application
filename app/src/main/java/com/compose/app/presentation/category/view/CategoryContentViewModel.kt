package com.compose.app.presentation.category.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.app.data.remote.product.model.product.ProductModelItem
import com.compose.app.domain.repository.ProductRepository
import com.compose.app.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CategoryContentViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    var productState: UiState<List<ProductModelItem>> by mutableStateOf(
        UiState.None()
    )
        private set

    fun getAllProductsByCategoryId(
        categoryId: Int, offset: Int,
        limit: Int
    ) = viewModelScope.launch {
        productState = UiState.Loading()
        productState = try {
            val productByCategoryModel =
                productRepository.getAllProductsByCategoryId(categoryId, offset, limit)
            if (productByCategoryModel.isEmpty()) {
                UiState.None(message = "No Product Available")
            } else {
                UiState.Success(productByCategoryModel)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Timber.wtf(e.message.toString())
            UiState.Error(e.message.toString())
        }
    }
}