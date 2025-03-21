package com.pdm.designsystems.component.pager

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.pdm.designsystems.theme.LocalDimensions
import com.pdm.designsystems.theme.LocalShapes

@Composable
fun UxPagerWithIndicator(
    pageCount: Int,
    modifier: Modifier = Modifier,
    state: PagerState,
    content: @Composable PagerScope.(pageIndex: Int) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = state
        ) { page ->
            content(page)
        }

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

        PageIndicator(
            pageCount = pageCount,
            currentPage = state.currentPage
        )
    }
}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(LocalDimensions.current.dimensions8),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage
            val shape = if (isSelected) {
                LocalShapes.current.pagerIndicator
            } else {
                LocalShapes.current.dots
            }
            val width = animateDpAsState(
                targetValue = if (isSelected) {
                    LocalDimensions.current.dimensions16
                } else {
                    LocalDimensions.current.dimensions8
                },
                label = "indicator width"
            )
            val color = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            }

            Box(
                modifier = Modifier
                    .height(LocalDimensions.current.dimensions8)
                    .width(width.value)
                    .clip(shape)
                    .background(
                        color = color
                    )
            )
        }
    }
}
