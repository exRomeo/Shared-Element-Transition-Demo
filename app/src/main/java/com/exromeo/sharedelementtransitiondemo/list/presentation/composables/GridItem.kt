package com.exromeo.sharedelementtransitiondemo.list.presentation.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.exromeo.sharedelementtransitiondemo.list.presentation.models.ProductUIModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GridItem(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    item: ProductUIModel,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .aspectRatio(0.75f)
            .sharedElement(
                state = rememberSharedContentState(key = item.id.toString() + "-container"),
                animatedVisibilityScope = animatedVisibilityScope,
            )
            .shadow(4.dp, shape = MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .aspectRatio(1f)
                    .sharedElement(
                        state = rememberSharedContentState(key = item.id.toString() + "-img"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        clipInOverlayDuringTransition = OverlayClip(
                            MaterialTheme.shapes.medium.copy(
                                bottomStart = CornerSize(0.dp),
                                bottomEnd = CornerSize(0.dp)
                            )
                        )
                    )
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceBright),
                model = item.thumbnail.orEmpty(),
                loading = {
                    Box(
                        modifier = Modifier.matchParentSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                },
                contentDescription = item.title
            )

            Text(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = item.id.toString() + "-discount"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .padding(2.dp)
                    .background(
                        MaterialTheme.colorScheme.errorContainer,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(2.dp),
                text = item.discountPercentage.toString() + "% off!",
                color = MaterialTheme.colorScheme.onErrorContainer,
                fontSize = MaterialTheme.typography.labelSmall.fontSize
            )

            Text(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = item.id.toString() + "-availability-status"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .padding(2.dp)
                    .background(
                        if (item.isAvailable) Color.Green else MaterialTheme.colorScheme.errorContainer,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(horizontal = 2.dp)
                    .align(Alignment.BottomEnd),
                text = item.availabilityStatus.orEmpty(),
                color = if (item.isAvailable) Color.Black else MaterialTheme.colorScheme.onErrorContainer,
                fontSize = MaterialTheme.typography.labelSmall.fontSize
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.sharedBounds(
                        sharedContentState = rememberSharedContentState(key = item.id.toString() + "-price"),
                        animatedVisibilityScope = animatedVisibilityScope
                    ),
                    text = createPriceString(
                        price = item.price,
                        discountPercentage = item.discountPercentage
                    ),
                    color = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.primaryContainer),
                    fontSize = MaterialTheme.typography.labelMedium.fontSize
                )

                Text(
                    modifier = Modifier.sharedBounds(
                        sharedContentState = rememberSharedContentState(key = item.id.toString() + "-rating"),
                        animatedVisibilityScope = animatedVisibilityScope
                    ),
                    text = "rating: " + item.rating.toString(),
                    fontSize = MaterialTheme.typography.labelSmall.fontSize
                )
            }

            Text(
                modifier = Modifier.sharedBounds(
                    sharedContentState = rememberSharedContentState(key = item.id.toString() + "-title"),
                    animatedVisibilityScope = animatedVisibilityScope
                ),
                text = item.title.orEmpty(),
                color = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.primaryContainer),
                fontSize = MaterialTheme.typography.labelMedium.fontSize
            )
        }
    }
}

fun calculateDiscount(price: Float?, discountPercentage: Float?): Float? {
    return price?.times(100f)?.minus(price.times(discountPercentage ?: 0f))?.toInt()?.div(100f)
}

@Composable
fun createPriceString(price: Float?, discountPercentage: Float?): AnnotatedString {
    return AnnotatedString.Builder().apply {
        append("LE ")
        if ((discountPercentage ?: 0f) > 0f) {
            append(calculateDiscount(price, discountPercentage)?.toString())
            append(" ")
            withStyle(
                SpanStyle(
                    textDecoration = TextDecoration.LineThrough,
                    color = MaterialTheme.colorScheme.error
                ),
            ) {
                append(price.toString())
            }
        } else
            append(price.toString())

    }.toAnnotatedString()
}