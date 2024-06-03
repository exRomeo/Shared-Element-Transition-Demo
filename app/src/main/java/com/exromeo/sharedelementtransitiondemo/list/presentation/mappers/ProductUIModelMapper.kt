package com.exromeo.sharedelementtransitiondemo.list.presentation.mappers

import com.exromeo.sharedelementtransitiondemo.list.presentation.models.ProductUIModel
import com.exromeo.sharedelementtransitiondemo.products.domain.models.ProductDomainModel

fun ProductDomainModel.toUI(): ProductUIModel =
    ProductUIModel(
        id = uniqueID,
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
        dimensions = dimensions?.toUI(),
        warrantyInformation = warrantyInformation,
        shippingInformation = shippingInformation,
        availabilityStatus = availabilityStatus,
        reviews = reviews?.map { it.toUI() },
        returnPolicy = returnPolicy,
        minimumOrderQuantity = minimumOrderQuantity,
        meta = meta?.toUI(),
        images = images,
        thumbnail = thumbnail,
    )