package com.pdm.designsystems.container

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun UnchainXSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val surface = MaterialTheme.colorScheme.surface
    val background = MaterialTheme.colorScheme.background

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        surface,
                        background
                    )
                )
            )
        }
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface
        ) {
            content()
        }
    }
}