package com.exromeo.sharedelementtransitiondemo.list.presentation.mappers

import com.exromeo.sharedelementtransitiondemo.list.presentation.models.ReviewUIModel
import com.exromeo.sharedelementtransitiondemo.products.domain.models.ReviewDomainModel

fun ReviewDomainModel.toUI(): ReviewUIModel =
    ReviewUIModel(
        rating = rating,
        comment = comment,
        date = date,
        reviewerName = reviewerName,
        reviewerEmail = reviewerEmail
    )