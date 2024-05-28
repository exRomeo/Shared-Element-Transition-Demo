package com.exromeo.sharedelementtransitiondemo.common.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.exromeo.sharedelementtransitiondemo.common.presentation.navigation.models.Destinations
import com.exromeo.sharedelementtransitiondemo.list.presentation.ListScreen

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destinations.List.route,
    ) {

        composable(Destinations.List.route) {
            ListScreen(
                navController = navController,
            )
        }

    }
}