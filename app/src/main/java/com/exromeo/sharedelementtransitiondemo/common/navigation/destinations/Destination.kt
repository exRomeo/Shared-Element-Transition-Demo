package com.exromeo.sharedelementtransitiondemo.common.navigation.destinations

import com.exromeo.sharedelementtransitiondemo.list.presentation.models.ProductUIModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

sealed interface Destination {

    @Serializable
    data object ListScreen : Destination


    @Serializable
    data class DetailsScreen(val navArg: String) : Destination


}
