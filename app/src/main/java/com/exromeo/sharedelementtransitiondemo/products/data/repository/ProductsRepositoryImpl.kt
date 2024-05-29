package com.exromeo.sharedelementtransitiondemo.products.data.repository

import com.exromeo.sharedelementtransitiondemo.products.data.mappers.toDomain
import com.exromeo.sharedelementtransitiondemo.products.data.remote.data_source.ProductsRemoteSource
import com.exromeo.sharedelementtransitiondemo.products.domain.models.ProductDomainModel
import com.exromeo.sharedelementtransitiondemo.products.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val remoteSource: ProductsRemoteSource,
) : ProductsRepository {

    override suspend fun getProducts(
        skip: Int,
        limit: Int
    ): List<ProductDomainModel> =
        remoteSource.getProducts(
            skip = skip,
            limit = limit
        )
            .products
            .map { it.toDomain() }

}