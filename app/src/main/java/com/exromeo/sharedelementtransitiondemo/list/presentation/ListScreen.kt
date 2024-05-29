package com.exromeo.sharedelementtransitiondemo.list.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.exromeo.sharedelementtransitiondemo.list.presentation.composables.FocusedItem
import com.exromeo.sharedelementtransitiondemo.list.presentation.composables.GridItem
import com.exromeo.sharedelementtransitiondemo.list.presentation.composables.ImagesListScreenHeader
import com.exromeo.sharedelementtransitiondemo.list.presentation.composables.createPriceString
import com.exromeo.sharedelementtransitiondemo.list.presentation.models.ProductUIModel
import com.exromeo.sharedelementtransitiondemo.list.presentation.models.ReviewUIModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ListScreen(modifier: Modifier = Modifier) {
    val viewModel: ProductsListingViewModel = hiltViewModel()
    val products = viewModel.products.collectAsState()
    val selectedProduct = viewModel.selectedProduct.collectAsState()

    BackHandler(selectedProduct.value != null) {
        selectedProduct.value?.onClick?.invoke()
    }

    SharedTransitionLayout {
        Column(modifier = modifier.fillMaxSize()) {
            ImagesListScreenHeader()
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp)
            ) {
                items(products.value) {
                    val visible by remember {
                        derivedStateOf { selectedProduct.value?.id != it.id }
                    }
                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn() + scaleIn(),
                        exit = fadeOut() + scaleOut(),
                    ) {
                        GridItem(
                            modifier = Modifier.padding(4.dp),
                            item = it,
                            animatedVisibilityScope = this@AnimatedVisibility,
                            transitionScope = this@SharedTransitionLayout,
                            onClick = it.onClick
                        )
                    }
                }
            }
        }


        AnimatedContent(
            modifier = Modifier.fillMaxSize(),
            targetState = selectedProduct.value,
            contentKey = { product -> product?.id.toString() },
            transitionSpec = {
                fadeIn() togetherWith  fadeOut()
            },
            label = "product details"
        ) { product ->
            product?.let {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            onClick = { product.onClick },
                            interactionSource = remember {
                                MutableInteractionSource()
                            },
                            indication = null
                        )
                        .background(MaterialTheme.colorScheme.scrim.copy(alpha = 0.4f))
                        .padding(horizontal = 20.dp, vertical = 40.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    FocusedItem(
                        modifier = Modifier.fillMaxSize(),
                        item = product,
                        animatedVisibilityScope = this@AnimatedContent,
                        transitionScope = this@SharedTransitionLayout,
                    )
                }
            }
        }
    }
}