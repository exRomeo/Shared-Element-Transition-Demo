package com.exromeo.sharedelementtransitiondemo.common.presentation.navigation.models

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Destinations(val route: String) {
    data object List : Destinations("list")
    data object Detail : Destinations("detail") {
        val args = listOf(
            navArgument("imageId") { type = NavType.IntType }
        )
    }
}