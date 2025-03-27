package com.pdm.designsystems.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.pdm.designsystems.component.preview.PreviewSurface
import com.pdm.designsystems.component.preview.UxPreview

@Composable
fun UxText(text: String, textType: TextType, textAlign: TextAlign = TextAlign.Left) {
    Text(
        text = text,
        style = textType.getStyle(),
        color = textType.getColor(),
        textAlign = textAlign
    )
}

@Composable
fun UxText(modifier: Modifier = Modifier, text: AnnotatedString, textType: TextType, textAlign: TextAlign = TextAlign.Left) {
    Text(
        modifier = modifier,
        text = text,
        style = textType.getStyle(),
        textAlign = textAlign
    )
}

@Composable
@UxPreview
fun AllTextPreview() {
    val text = LoremIpsum(10).values.first()
    PreviewSurface {
        UxText(
            text = text,
            textType = TextType.DISPLAY_SMALL_GRADIENT
        )
        UxText(
            text = text,
            textType = TextType.DISPLAY_MEDIUM
        )
        UxText(
            text = text,
            textType = TextType.HEADLINE_SMALL
        )
        UxText(
            text = text,
            textType = TextType.TITLE
        )
        UxText(
            text = text,
            textType = TextType.LABEL_SMALL
        )
        UxText(
            text = text,
            textType = TextType.LABEL_LARGE
        )
        UxText(
            text = text,
            textType = TextType.BUTTON_LABEL
        )
    }
}
