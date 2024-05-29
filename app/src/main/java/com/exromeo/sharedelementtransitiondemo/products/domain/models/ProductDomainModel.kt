package com.exromeo.sharedelementtransitiondemo.products.domain.models

data class ProductDomainModel(
    private val id: Int?,
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
    val dimensions: DimensionsDomainModel?,
    val warrantyInformation: String?,
    val shippingInformation: String?,
    val availabilityStatus: String?,
    val reviews: List<ReviewDomainModel>?,
    val returnPolicy: String?,
    val minimumOrderQuantity: Int?,
    val meta: MetaDomainModel?,
    val images: List<String>?,
    val thumbnail: String?
) {
    val uniqueID: Int = id ?: hashCode()
}

data class DimensionsDomainModel(
    val width: Float?,
    val height: Float?,
    val depth: Float?
)

data class MetaDomainModel(
    val createdAt: String?,
    val updatedAt: String?,
    val barcode: String?,
    val qrCode: String?
)

data class ReviewDomainModel(
    val rating: Int?,
    val comment: String?,
    val date: String?,
    val reviewerName: String?,
    val reviewerEmail: String?
)