package com.compose.app.presentation.checkout.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.app.data.remote.product.model.product.ProductModelItem
import com.compose.app.domain.repository.ProductDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val productDBRepository: ProductDBRepository
) : ViewModel() {
    var products: List<ProductModelItem> = emptyList()

    init {
        productDBRepository.getAllProducts()
            .onEach {
                products = it
            }
            .launchIn(viewModelScope)
    }

    fun clearProductDB() = viewModelScope.launch {
        productDBRepository.deleteAllProducts()
    }
}