package com.exromeo.sharedelementtransitiondemo.products.data.remote.retrofit.response

import com.exromeo.sharedelementtransitiondemo.products.data.models.ProductDataModel
import com.google.gson.annotations.SerializedName

data class ProductsListResponse(
    @SerializedName("products")
    val products: List<ProductDataModel>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("limit")
    val limit: Int
)
