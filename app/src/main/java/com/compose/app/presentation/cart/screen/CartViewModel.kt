package com.compose.app.presentation.cart.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.app.data.remote.product.model.product.ProductModelItem
import com.compose.app.domain.repository.ProductDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val productDBRepository: ProductDBRepository
) : ViewModel() {


    //stateIn is under the hood doing what is happening below
    //shareIn retrun sharedFlow
    /*
    private val _getAllSub = MutableStateFlow(listOf<Model>())
    val getAllSub: StateFlow<List<Model>> = _getAllSub.asStateFlow()

    init {
    viewModelScope.launch {
        repo.allItems
            .catch { exception -> exception.localizedMessage?.let { Log.e("TAG", it) } }
            .collect { model ->
                _getAllSub.value = model
            }
        }
    }
     */

    val products: StateFlow<List<ProductModelItem>> = productDBRepository.getAllProducts()
        .catch { exception -> exception.localizedMessage?.let { Timber.wtf(it) } }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly, emptyList()
        )

    fun deleteProduct(product: ProductModelItem) = viewModelScope.launch {
        try {
            productDBRepository.deleteProduct(product)
        } catch (e: Exception) {
            Timber.wtf(e.message.toString())
        }
    }

    fun updateProductQuantity(productQuantity: Int, productId: Int) = viewModelScope.launch {
        try {
            productDBRepository.updateProductQuantity(productQuantity, productId)
        } catch (e: Exception) {
            Timber.wtf(e.message.toString())
        }
    }
}