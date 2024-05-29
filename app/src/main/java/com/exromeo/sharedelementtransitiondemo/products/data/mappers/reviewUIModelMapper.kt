package com.exromeo.sharedelementtransitiondemo.products.data.mappers

import com.exromeo.sharedelementtransitiondemo.products.data.models.ReviewDataModel
import com.exromeo.sharedelementtransitiondemo.products.domain.models.ReviewDomainModel

fun ReviewDataModel.toDomain(): ReviewDomainModel =
    ReviewDomainModel(
        rating = rating,
        comment = comment,
        date = date,
        reviewerName = reviewerName,
        reviewerEmail = reviewerEmail
    )