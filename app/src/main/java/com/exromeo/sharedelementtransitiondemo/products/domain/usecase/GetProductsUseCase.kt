package com.exromeo.sharedelementtransitiondemo.products.domain.usecase

import com.exromeo.sharedelementtransitiondemo.products.domain.models.ProductDomainModel
import com.exromeo.sharedelementtransitiondemo.products.domain.repository.ProductsRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductsRepository
) {

    suspend operator fun invoke(
        skip: Int,
        limit: Int
    ): List<ProductDomainModel> = repository
        .getProducts(skip, limit)
}