package com.exromeo.sharedelementtransitiondemo.list.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProductsListScreenHeader(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState
) {
    CompositionLocalProvider(
        LocalContentColor provides MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.secondaryContainer),
        LocalTextStyle provides MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current)
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(horizontal = 20.dp)
                .windowInsetsPadding(WindowInsets.statusBars)
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Products list",
                color = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.secondaryContainer)
            )
        }
    }
}