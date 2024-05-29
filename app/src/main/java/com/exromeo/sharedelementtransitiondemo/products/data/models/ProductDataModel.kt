package com.exromeo.sharedelementtransitiondemo.products.data.models

data class ProductDataModel (
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val category: String? = null,
    val price: Float? = null,
    val discountPercentage: Float? = null,
    val rating: Float? = null,
    val stock: Int? = null,
    val tags: List<String>? = null,
    val sku: String? = null,
    val weight: Int? = null,
    val dimensions: DimensionsDataModel? = null,
    val warrantyInformation: String? = null,
    val shippingInformation: String? = null,
    val availabilityStatus: String? = null,
    val reviews: List<ReviewDataModel>? = null,
    val returnPolicy: String? = null,
    val minimumOrderQuantity: Int? = null,
    val meta: MetaDataModel? = null,
    val images: List<String>? = null,
    val thumbnail: String? = null
)

data class DimensionsDataModel (
    val width: Float? = null,
    val height: Float? = null,
    val depth: Float? = null
)

data class MetaDataModel (
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val barcode: String? = null,
    val qrCode: String? = null
)

data class ReviewDataModel (
    val rating: Int? = null,
    val comment: String? = null,
    val date: String? = null,
    val reviewerName: String? = null,
    val reviewerEmail: String? = null
)
