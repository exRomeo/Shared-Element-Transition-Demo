package com.exromeo.sharedelementtransitiondemo.products.domain.repository

import com.exromeo.sharedelementtransitiondemo.products.domain.models.ProductDomainModel

interface ProductsRepository {
    suspend fun getProducts(skip: Int, limit: Int): List<ProductDomainModel>
    suspend fun searchProducts(skip: Int, limit: Int, query: String): List<ProductDomainModel>
}