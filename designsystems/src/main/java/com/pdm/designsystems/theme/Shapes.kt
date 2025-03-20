package com.pdm.designsystems.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class Shapes(
    val button: Shape = RoundedCornerShape(8.dp),
    val dots: Shape = CircleShape,
    val pagerIndicator: Shape = RoundedCornerShape(16.dp),
    val bar: Shape = CutCornerShape(topStart = 6.dp, topEnd = 6.dp)
)

val LocalShapes = compositionLocalOf { Shapes() }
