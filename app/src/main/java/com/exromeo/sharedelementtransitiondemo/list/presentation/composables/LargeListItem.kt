package com.exromeo.sharedelementtransitiondemo.list.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.exromeo.sharedelementtransitiondemo.list.presentation.models.Image

@Composable
fun LargeListItem(
    modifier: Modifier = Modifier,
    item: Image,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .height(200.dp),
            contentScale = ContentScale.FillWidth,
            model = item.imageId,
            contentDescription = item.desc
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            text = item.desc,
            color = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.primaryContainer)
        )
    }
}