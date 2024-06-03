package com.exromeo.sharedelementtransitiondemo.list.presentation

import android.util.Log
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exromeo.sharedelementtransitiondemo.list.presentation.mappers.toUI
import com.exromeo.sharedelementtransitiondemo.list.presentation.models.ProductUIModel
import com.exromeo.sharedelementtransitiondemo.products.domain.usecase.GetProductsUseCase
import com.exromeo.sharedelementtransitiondemo.products.domain.usecase.SearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsListingViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    private val _products: MutableStateFlow<List<ProductUIModel>> = MutableStateFlow(emptyList())
    val products = _products.asStateFlow()

    val textFieldState = TextFieldState()

    init {
        getProductsList()
        onTextChanged()
    }

    private fun getProductsList() {
        viewModelScope.launch {
            _products.update {
                getProductsUseCase(
                    0,
                    0
                ).map { it.toUI() }
            }
        }
    }

    private fun searchProducts(query: String) {
        viewModelScope.launch {
            _products.update {
                searchProductsUseCase(
                    0,
                    0,
                    query
                ).map { it.toUI() }
            }
        }
    }

    private fun onTextChanged() {
        viewModelScope.launch {
            snapshotFlow { textFieldState.text }.collectLatest { text ->
                delay(500)
                if (text.isNotEmpty()) {
                    searchProducts(text.toString())
                } else {
                    getProductsList()
                }
            }
        }
    }
}