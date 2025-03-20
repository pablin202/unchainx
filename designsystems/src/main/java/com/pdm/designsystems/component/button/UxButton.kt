package com.pdm.designsystems.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.pdm.designsystems.component.preview.PreviewSurface
import com.pdm.designsystems.component.preview.UxPreview
import com.pdm.designsystems.component.text.UxText
import com.pdm.designsystems.theme.LocalDimensions
import com.pdm.designsystems.theme.LocalShapes

@Composable
fun UxButton(
    text: String,
    buttonType: ButtonType = ButtonType.FILLED_TOTAL,
    icon: Int? = null,
    isFullWidth: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = buttonType.getButtonColor(),
        modifier = Modifier.buttonWidth(isFullWidth),
        elevation = buttonType.getButtonElevation(),
        shape = LocalShapes.current.button
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            icon?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = "$text button",
                    modifier = Modifier.size(LocalDimensions.current.dimensions24)
                )
                Spacer(modifier = Modifier.width(LocalDimensions.current.dimensions8))
            }
            UxText(
                text = text,
                textType = buttonType.getTextType()
            )
        }
    }
}

@Composable
@UxPreview
fun ButtonPreview() {
    PreviewSurface {
        UxButton(
            text = "Click Me",
            isFullWidth = false
        ) { }
        UxButton(
            text = "Click Me",
            isFullWidth = true
        ) { }
        UxButton(
            text = "Click Me",
            isFullWidth = true,
            buttonType = ButtonType.TEXT
        ) { }
    }
}
