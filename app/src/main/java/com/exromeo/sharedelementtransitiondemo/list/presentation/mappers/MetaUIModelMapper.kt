package com.exromeo.sharedelementtransitiondemo.list.presentation.mappers

import com.exromeo.sharedelementtransitiondemo.list.presentation.models.MetaUIModel
import com.exromeo.sharedelementtransitiondemo.products.data.models.MetaDataModel
import com.exromeo.sharedelementtransitiondemo.products.domain.models.MetaDomainModel

fun MetaDomainModel.toUI(): MetaUIModel =
    MetaUIModel(
        createdAt = createdAt,
        updatedAt = updatedAt,
        barcode = barcode,
        qrCode = qrCode
    )