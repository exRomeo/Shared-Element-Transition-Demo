package com.exromeo.sharedelementtransitiondemo.details.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.exromeo.sharedelementtransitiondemo.list.presentation.models.Image

@Composable
fun DetailsScreenTopSection(
    modifier: Modifier = Modifier,
    image: Image?,
    onBackClicked: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(),
            model = image?.imageId,
            contentDescription = image?.desc,
        )

        // back button
        IconButton(
            modifier = Modifier
                .padding(20.dp)
                .windowInsetsPadding(WindowInsets.statusBars)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer.copy(0.3f))
                .align(Alignment.TopStart),
            onClick = onBackClicked
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null
            )
        }
    }
}