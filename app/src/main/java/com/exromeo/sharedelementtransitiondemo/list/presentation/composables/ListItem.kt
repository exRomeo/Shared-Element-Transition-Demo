package com.exromeo.sharedelementtransitiondemo.list.presentation.composables

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.exromeo.sharedelementtransitiondemo.list.presentation.models.Image

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    animatedContentScope: AnimatedContentScope,
    transitionScope: SharedTransitionScope,
    item: Image,
    onClick: () -> Unit
) = with(transitionScope) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(64.dp)
                .sharedElement(
                    state = rememberSharedContentState(key = item.imageId),
                    animatedVisibilityScope = animatedContentScope
                )
                .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)),
            model = item.imageId,
            contentDescription = item.desc
        )
        Text(
            modifier = Modifier.sharedBounds(
                sharedContentState = rememberSharedContentState(key = item.desc),
                animatedVisibilityScope = animatedContentScope
            ),
            text = item.desc,
            color = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.primaryContainer)
        )
    }
}