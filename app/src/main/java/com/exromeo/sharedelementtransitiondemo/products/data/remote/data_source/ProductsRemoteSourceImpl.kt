package com.exromeo.sharedelementtransitiondemo.products.data.remote.data_source

import com.exromeo.sharedelementtransitiondemo.products.data.remote.retrofit.response.ProductsListResponse
import com.exromeo.sharedelementtransitiondemo.products.data.remote.retrofit.service.ProductsService
import javax.inject.Inject

class ProductsRemoteSourceImpl @Inject constructor(
    private val service: ProductsService
) : ProductsRemoteSource {

    override suspend fun getProducts(
        skip: Int,
        limit: Int
    ): ProductsListResponse =
        service.getProducts(skip, limit)

    override suspend fun searchProducts(
        skip: Int,
        limit: Int,
        query: String
    ): ProductsListResponse =
        service.searchProducts(skip, limit, query)

}