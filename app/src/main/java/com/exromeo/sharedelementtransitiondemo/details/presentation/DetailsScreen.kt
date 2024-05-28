package com.exromeo.sharedelementtransitiondemo.details.presentation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .sharedElement(
                    state = rememberSharedContentState(key = image?.imageId ?: 0),
                    animatedVisibilityScope = animatedContentScope,
                )
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = image?.imageId,
                contentDescription = image?.desc,
            )

            IconButton(
                modifier = Modifier
                    .padding(20.dp)
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(0.3f))
                    .align(Alignment.TopStart),
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
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