package com.exromeo.sharedelementtransitiondemo.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exromeo.sharedelementtransitiondemo.list.presentation.mappers.toUI
import com.exromeo.sharedelementtransitiondemo.list.presentation.models.ProductUIModel
import com.exromeo.sharedelementtransitiondemo.products.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsListingViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _products: MutableStateFlow<List<ProductUIModel>> = MutableStateFlow(emptyList())
    val products = _products.asStateFlow()

    private val _selectedProduct: MutableStateFlow<ProductUIModel?> = MutableStateFlow(null)
    val selectedProduct = _selectedProduct.asStateFlow()

    init {
        getProductsList()
    }

    private fun getProductsList() {
        viewModelScope.launch {
            _products.update {
                getProductsUseCase(
                    0,
                    0
                ).map { it.toUI { onProductSelected(it.toUI { onProductSelected(null) }) } }
            }
        }
    }

    private fun onProductSelected(product: ProductUIModel?) {
        _selectedProduct.update { product }
    }
}