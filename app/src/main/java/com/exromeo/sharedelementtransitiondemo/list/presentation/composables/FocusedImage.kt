package com.exromeo.sharedelementtransitiondemo.list.presentation.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.exromeo.sharedelementtransitiondemo.list.presentation.models.Image

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.FocusedImage(
    image: State<Image?>,
    modifier: Modifier = Modifier,
    onConfirmClick: () -> Unit
) {
    AnimatedContent(
        modifier = modifier,
        targetState = image.value,
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        },
        label = "animated image"
    ) { targetImage ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            targetImage?.let {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(onClick = onConfirmClick)
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                )

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = targetImage.desc),
                            animatedVisibilityScope = this@AnimatedContent,
                            clipInOverlayDuringTransition = OverlayClip(MaterialTheme.shapes.medium)
                        )
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    ImageContent(
                        image = targetImage,
                        onClick = onConfirmClick
                    )
                }
            }
        }
    }
}

@Composable
private fun ImageContent(
    modifier: Modifier = Modifier,
    image: Image,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        AsyncImage(
            model = image.imageId,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(20f / 9f),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(
            text = image.desc,
            modifier = Modifier
                .wrapContentWidth()
                .padding(8.dp),
            style = MaterialTheme.typography.titleSmall
        )
    }
}