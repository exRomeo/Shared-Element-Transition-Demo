package com.exromeo.sharedelementtransitiondemo.products.data.remote.retrofit.service

import com.exromeo.sharedelementtransitiondemo.products.data.remote.retrofit.response.ProductsListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsService {

    /**
     * You can pass [limit] and [skip] params to limit and skip the results for pagination,
     * and use [limit]=0 to get all items
     * */
    @GET("/products")
    suspend fun getProducts(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): ProductsListResponse
}

