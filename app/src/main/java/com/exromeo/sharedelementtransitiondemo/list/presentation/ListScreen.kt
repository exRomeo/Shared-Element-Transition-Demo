package com.exromeo.sharedelementtransitiondemo.list.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.exromeo.sharedelementtransitiondemo.common.navigation.destinations.Destination
import com.exromeo.sharedelementtransitiondemo.list.presentation.composables.GridItem
import com.exromeo.sharedelementtransitiondemo.list.presentation.composables.ProductsListScreenHeader

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    val viewModel: ProductsListingViewModel = hiltViewModel()
    val products = viewModel.products.collectAsState()
    val textFieldState = viewModel.textFieldState


    Column(modifier = modifier.fillMaxSize()) {
        ProductsListScreenHeader(textFieldState = textFieldState)
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp)
        ) {
            items(products.value) {
                GridItem(
                    modifier = Modifier.padding(4.dp),
                    item = it,
                    animatedVisibilityScope = animatedVisibilityScope,
                    onClick = { navController.navigate(Destination.DetailsScreen(navArg = it.toJson())) }
                )
            }
        }
    }
}