package com.exromeo.sharedelementtransitiondemo.common.navigation.destinations

import kotlinx.serialization.Serializable

sealed interface Destination {

    @Serializable
    data object ListScreen : Destination


    @Serializable
    data class DetailsScreen(val navArg: String) : Destination


}
