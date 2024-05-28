package com.exromeo.sharedelementtransitiondemo.list.presentation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.exromeo.sharedelementtransitiondemo.common.presentation.navigation.models.Destinations
import com.exromeo.sharedelementtransitiondemo.list.presentation.composables.ImagesListScreenHeader
import com.exromeo.sharedelementtransitiondemo.list.presentation.composables.ListItem
import com.exromeo.sharedelementtransitiondemo.list.presentation.models.Image

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    animatedContentScope: AnimatedContentScope,
    transitionScope: SharedTransitionScope,
    navController: NavController
) {
    Column(modifier = modifier.fillMaxSize()) {
        ImagesListScreenHeader()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 16.dp)
        ) {
            items(Image.list) {
                ListItem(
                    item = it,
                    animatedContentScope = animatedContentScope,
                    transitionScope = transitionScope,
                    onClick = { navController.navigate(Destinations.Detail.route + "/${it.id}") }
                )
            }
        }
    }
}