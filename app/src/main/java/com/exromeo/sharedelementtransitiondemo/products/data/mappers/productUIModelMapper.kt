package com.exromeo.sharedelementtransitiondemo.products.data.mappers

import com.exromeo.sharedelementtransitiondemo.products.data.models.ProductDataModel
import com.exromeo.sharedelementtransitiondemo.products.domain.models.ProductDomainModel

fun ProductDataModel.toDomain(): ProductDomainModel =
    ProductDomainModel(
        id = id,
        title = title,
        description = description,
        category = category,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        tags = tags,
        sku = sku,
        weight = weight,
        dimensions = dimensions?.toDomain(),
        warrantyInformation = warrantyInformation,
        shippingInformation = shippingInformation,
        availabilityStatus = availabilityStatus,
        reviews = reviews?.map { it.toDomain() },
        returnPolicy = returnPolicy,
        minimumOrderQuantity = minimumOrderQuantity,
        meta = meta?.toDomain(),
        images = images,
        thumbnail = thumbnail
    )