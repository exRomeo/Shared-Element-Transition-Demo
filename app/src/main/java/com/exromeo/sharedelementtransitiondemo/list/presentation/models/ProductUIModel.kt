package com.exromeo.sharedelementtransitiondemo.list.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Serializable
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
) {
    val isAvailable
        get() = availabilityStatus == "In Stock"

    fun toJson(): String = Json.encodeToString<ProductUIModel>(this)

    companion object {
        fun fromJson(json: String): ProductUIModel? =
            kotlin.runCatching { Json.decodeFromString<ProductUIModel>(json) }.getOrNull()
    }
}


@Serializable
data class DimensionsUIModel(
    val width: Float?,
    val height: Float?,
    val depth: Float?
)


@Serializable
data class MetaUIModel(
    val createdAt: String?,
    val updatedAt: String?,
    val barcode: String?,
    val qrCode: String?
)


@Serializable
data class ReviewUIModel(
    val rating: Int?,
    val comment: String?,
    val date: String?,
    val reviewerName: String?,
    val reviewerEmail: String?
)
