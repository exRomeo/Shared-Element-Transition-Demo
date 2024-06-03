package com.exromeo.sharedelementtransitiondemo.common.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.exromeo.sharedelementtransitiondemo.common.navigation.destinations.Destination
import com.exromeo.sharedelementtransitiondemo.list.presentation.ListScreen
import com.exromeo.sharedelementtransitiondemo.list.presentation.composables.FocusedItem
import com.exromeo.sharedelementtransitiondemo.list.presentation.models.ProductUIModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainNav(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    SharedTransitionLayout {
        NavHost(navController = navController, startDestination = Destination.ListScreen) {

            composable<Destination.ListScreen> {
                ListScreen(
                    navController = navController,
                    animatedVisibilityScope = this,
                )
            }

            composable<Destination.DetailsScreen> { navBackStackEntry ->
                val product =   ProductUIModel.fromJson(navBackStackEntry.toRoute<Destination.DetailsScreen>().navArg)
                product?.let {
                    DetailsScreen(
                        product = product,
                        animatedVisibilityScope = this
                    )
                }

            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    modifier: Modifier = Modifier,
    product: ProductUIModel,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        FocusedItem(
            modifier = Modifier.fillMaxSize(),
            item = product,
            animatedVisibilityScope = animatedVisibilityScope,
        )
    }

}
