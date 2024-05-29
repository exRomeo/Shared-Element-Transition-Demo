package com.exromeo.sharedelementtransitiondemo.list.presentation.models

data class ProductUIModel(
    val id: Int,
    val title: String?,
    val description: String?,
    val category: String?,
    val price: Float?,
    val discountPercentage: Float?,
    val rating: Float?,
    val stock: Int?,
    val tags: List<String>?,
    val sku: String?,
    val weight: Int?,
    val dimensions: DimensionsUIModel?,
    val warrantyInformation: String?,
    val shippingInformation: String?,
    val availabilityStatus: String?,
    val reviews: List<ReviewUIModel>?,
    val returnPolicy: String?,
    val minimumOrderQuantity: Int?,
    val meta: MetaUIModel?,
    val images: List<String>?,
    val thumbnail: String?,
    val onClick: () -> Unit
){
    val isAvailable
        get() =  availabilityStatus == "In Stock"
}

data class DimensionsUIModel(
    val width: Float?,
    val height: Float?,
    val depth: Float?
)

data class MetaUIModel(
    val createdAt: String?,
    val updatedAt: String?,
    val barcode: String?,
    val qrCode: String?
)

data class ReviewUIModel(
    val rating: Int?,
    val comment: String?,
    val date: String?,
    val reviewerName: String?,
    val reviewerEmail: String?
)