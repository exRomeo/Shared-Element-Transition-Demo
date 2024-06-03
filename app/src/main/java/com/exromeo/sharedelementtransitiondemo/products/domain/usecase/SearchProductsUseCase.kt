package com.exromeo.sharedelementtransitiondemo.products.domain.usecase

import com.exromeo.sharedelementtransitiondemo.products.domain.repository.ProductsRepository
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(private val repository: ProductsRepository) {
    suspend operator fun invoke(skip: Int, limit: Int, query: String) =
        repository.searchProducts(skip, limit, query)
}