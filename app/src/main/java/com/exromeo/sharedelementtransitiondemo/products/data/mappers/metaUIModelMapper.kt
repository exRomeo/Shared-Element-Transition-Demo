package com.exromeo.sharedelementtransitiondemo.products.data.mappers

import com.exromeo.sharedelementtransitiondemo.products.data.models.MetaDataModel
import com.exromeo.sharedelementtransitiondemo.products.domain.models.MetaDomainModel

fun MetaDataModel.toDomain(): MetaDomainModel =
    MetaDomainModel(
        createdAt = createdAt,
        updatedAt = updatedAt,
        barcode = barcode,
        qrCode = qrCode
    )