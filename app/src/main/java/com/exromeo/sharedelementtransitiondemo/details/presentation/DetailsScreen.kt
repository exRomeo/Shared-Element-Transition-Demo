package com.exromeo.sharedelementtransitiondemo.details.presentation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.exromeo.sharedelementtransitiondemo.details.presentation.composables.DetailsScreenTopSection
import com.exromeo.sharedelementtransitiondemo.list.presentation.models.Image

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    modifier: Modifier = Modifier,
    animatedContentScope: AnimatedContentScope,
    imageId: Int?,
    navController: NavController
) {
    val image = remember { Image.getItem(imageId) }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Shared Transition Element: used the image id as key to identify this element
        // notice here i marked the container as a shared element and not the photo as it will be the
        // same size as the photo and also can contain another UI element which in this case is the back button
        DetailsScreenTopSection(
            modifier = Modifier.sharedElement(
                state = rememberSharedContentState(key = image?.imageId ?: 0),
                animatedVisibilityScope = animatedContentScope,
            ),
            image = image,
            onBackClicked = { navController.popBackStack() })


        // Shared Transition Element: used the text as key to identify this element
        Text(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = image?.desc ?: "0"),
                    animatedVisibilityScope = animatedContentScope,
                )
                .padding(horizontal = 20.dp),
            text = image?.desc.orEmpty(),
            style = MaterialTheme.typography.titleLarge
        )
    }
}