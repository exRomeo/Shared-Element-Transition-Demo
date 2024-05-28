package com.exromeo.sharedelementtransitiondemo.common.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.exromeo.sharedelementtransitiondemo.common.presentation.navigation.models.Destinations
import com.exromeo.sharedelementtransitiondemo.details.presentation.DetailsScreen
import com.exromeo.sharedelementtransitiondemo.list.presentation.ListScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    SharedTransitionLayout {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = Destinations.List.route,
        ) {

            composable(Destinations.List.route) {
                ListScreen(
                    navController = navController,
                    animatedContentScope = this,
                    transitionScope = this@SharedTransitionLayout,
                )
            }

            composable(
                Destinations.Detail.route + "/{imageId}",
                arguments = Destinations.Detail.args
            ) { backStackEntry ->
                val imageId = backStackEntry.arguments?.getInt("imageId")

                DetailsScreen(
                    navController = navController,
                    animatedContentScope = this,
                    imageId = imageId
                )
            }
        }
    }
}