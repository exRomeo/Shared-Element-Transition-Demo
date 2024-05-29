package com.exromeo.sharedelementtransitiondemo.products.data.remote.data_source

import com.exromeo.sharedelementtransitiondemo.products.data.models.ProductDataModel
import com.exromeo.sharedelementtransitiondemo.products.data.remote.retrofit.response.ProductsListResponse

interface ProductsRemoteSource {

    suspend fun getProducts(skip: Int, limit: Int): ProductsListResponse
}