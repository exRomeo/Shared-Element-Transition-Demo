package com.exromeo.sharedelementtransitiondemo.list.presentation.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.exromeo.sharedelementtransitiondemo.list.presentation.models.ProductUIModel
import com.exromeo.sharedelementtransitiondemo.list.presentation.models.ReviewUIModel

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalFoundationApi::class)
@Composable
fun FocusedItem(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    transitionScope: SharedTransitionScope,
    item: ProductUIModel,
) = with(transitionScope) {
    Column(
        modifier = modifier
            .sharedElement(
                state = rememberSharedContentState(key = item.id.toString() + "-container"),
                animatedVisibilityScope = animatedVisibilityScope
            )
            .shadow(4.dp, shape = MaterialTheme.shapes.medium)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .verticalScroll(rememberScrollState())

    ) {
        val pagerState = rememberPagerState {
            item.images?.size ?: 0
        }
        HorizontalPager(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = item.id.toString() + "-img"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    clipInOverlayDuringTransition = OverlayClip(
                        MaterialTheme.shapes.medium.copy(
                            bottomStart = CornerSize(0.dp),
                            bottomEnd = CornerSize(0.dp)
                        )
                    )
                )
                .fillMaxWidth()
                .aspectRatio(2f)
                .background(MaterialTheme.colorScheme.surfaceBright),
            state = pagerState
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = item.images?.getOrNull(it).orEmpty(),
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

        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = item.id.toString() + "-title"),
                    animatedVisibilityScope = animatedVisibilityScope
                ),
            text = item.title.orEmpty(),
            color = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.primaryContainer),
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
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
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )

            Text(
                modifier = Modifier.sharedBounds(
                    sharedContentState = rememberSharedContentState(key = item.id.toString() + "-rating"),
                    animatedVisibilityScope = animatedVisibilityScope
                ),
                text = "rating: " + item.rating.toString(),
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
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
                    .padding(horizontal = 2.dp),
                text = item.availabilityStatus.orEmpty(),
                color = if (item.isAvailable) Color.Black else MaterialTheme.colorScheme.onErrorContainer,
                fontSize = MaterialTheme.typography.labelSmall.fontSize
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = item.description.orEmpty(),
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )
        item.reviews?.let {
            Text(
                modifier = Modifier.padding(20.dp),
                text = "User Reviews : ",
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.Bold
            )

            item.reviews.forEach { review ->
                ReviewBox(review = review)
            }
        }
    }
}


@Composable
private fun ReviewBox(modifier: Modifier = Modifier, review: ReviewUIModel) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .border(
                1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.medium
            )
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(text = review.reviewerName?.take(2).orEmpty())
            }
            Text(text = review.reviewerName.orEmpty())
        }
        Text(modifier = Modifier.padding(horizontal = 40.dp), text = review.comment.orEmpty())
    }
}