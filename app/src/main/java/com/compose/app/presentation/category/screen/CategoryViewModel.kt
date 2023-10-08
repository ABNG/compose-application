package com.compose.app.presentation.category.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.app.data.remote.product.model.category.CategoryModelItem
import com.compose.app.domain.repository.ProductRepository
import com.compose.app.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    var categoryState: UiState<List<CategoryModelItem>> by mutableStateOf(
        UiState.None()
    )
        private set

    fun getAllCategories(limit: Int) = viewModelScope.launch {
        categoryState = UiState.Loading()
        categoryState = try {
            val categoryModel = productRepository.getAllCategories(limit)
            if (categoryModel.isEmpty()) {
                UiState.None(message = "No Category Available")
            } else {
                UiState.Success(categoryModel)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Timber.wtf(e.message.toString())
            UiState.Error(e.message.toString())
        }
    }

}