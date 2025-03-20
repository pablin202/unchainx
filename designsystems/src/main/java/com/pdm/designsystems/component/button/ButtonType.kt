package com.pdm.designsystems.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pdm.designsystems.component.text.TextType
import com.pdm.designsystems.theme.LocalDimensions

enum class ButtonType {
    FILLED_TOTAL,
    TEXT,
    ELEVATED
}

@Composable
fun ButtonType.getTextType() =
    when (this) {
        ButtonType.FILLED_TOTAL -> TextType.BUTTON_LABEL
        ButtonType.TEXT -> TextType.LABEL_SMALL
        ButtonType.ELEVATED -> TextType.LABEL_LARGE
    }

@Composable
fun ButtonType.getButtonElevation() =
    when (this) {
        ButtonType.FILLED_TOTAL -> ButtonDefaults.filledTonalButtonElevation()
        ButtonType.TEXT -> ButtonDefaults.buttonElevation()
        ButtonType.ELEVATED -> ButtonDefaults.elevatedButtonElevation()
    }

@Composable
fun ButtonType.getButtonColor() =
    when (this) {
        ButtonType.FILLED_TOTAL -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )

        ButtonType.TEXT -> ButtonDefaults.textButtonColors()
        ButtonType.ELEVATED -> ButtonDefaults.elevatedButtonColors()
    }

@Composable
fun Modifier.buttonWidth(isFullWidth: Boolean) =
    if (isFullWidth) {
        fillMaxWidth()
    } else {
        width(LocalDimensions.current.dimensions180)
    }.height(LocalDimensions.current.dimensions56)
