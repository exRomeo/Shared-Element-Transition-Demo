package com.exromeo.sharedelementtransitiondemo.list.presentation.mappers

import com.exromeo.sharedelementtransitiondemo.list.presentation.models.DimensionsUIModel
import com.exromeo.sharedelementtransitiondemo.products.data.models.DimensionsDataModel
import com.exromeo.sharedelementtransitiondemo.products.domain.models.DimensionsDomainModel

fun DimensionsDomainModel.toUI(): DimensionsUIModel =
    DimensionsUIModel(
        width = width,
        height = height,
        depth = depth
    )
