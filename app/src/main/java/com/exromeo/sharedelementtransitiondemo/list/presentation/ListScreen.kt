package com.exromeo.sharedelementtransitiondemo.list.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.exromeo.sharedelementtransitiondemo.list.presentation.composables.FocusedImage
import com.exromeo.sharedelementtransitiondemo.list.presentation.composables.LargeListItem
import com.exromeo.sharedelementtransitiondemo.list.presentation.composables.ImagesListScreenHeader
import com.exromeo.sharedelementtransitiondemo.list.presentation.models.Image

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalFoundationApi::class)
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(modifier = modifier.fillMaxSize()) {
        ImagesListScreenHeader()
        SharedTransitionLayout {

            val selected = remember { mutableStateOf<Image?>(null) }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 16.dp)
            ) {
                items(Image.list) {
                    val visible by remember {
                        derivedStateOf {
                            selected.value != it
                        }
                    }
                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn() + scaleIn(),
                        exit = fadeOut() + scaleOut(),
                        modifier = Modifier.animateItemPlacement()
                    ) {
                        LargeListItem(
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .sharedBounds(
                                    sharedContentState = rememberSharedContentState(key = it.desc),
                                    // Using the scope provided by AnimatedVisibility
                                    animatedVisibilityScope = this,
                                    clipInOverlayDuringTransition = OverlayClip(MaterialTheme.shapes.medium)
                                ),
                            item = it,
                            onClick = { selected.value = if (it == selected.value) null else it }
                        )
                    }
                }
            }

            FocusedImage(image = selected, onConfirmClick = {
                selected.value = null
            }
            )
        }
    }
}