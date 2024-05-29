package com.exromeo.sharedelementtransitiondemo.products.data.mappers

import com.exromeo.sharedelementtransitiondemo.products.data.models.DimensionsDataModel
import com.exromeo.sharedelementtransitiondemo.products.domain.models.DimensionsDomainModel

fun DimensionsDataModel.toDomain(): DimensionsDomainModel =
    DimensionsDomainModel(
        width = width,
        height = height,
        depth = depth
    )
