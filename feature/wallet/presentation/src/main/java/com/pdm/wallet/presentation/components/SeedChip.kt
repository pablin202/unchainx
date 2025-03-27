package com.pdm.wallet.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pdm.designsystems.component.text.TextType
import com.pdm.designsystems.component.text.UxText

@Composable
fun SeedChip(modifier: Modifier = Modifier, word: String, showOrder: Boolean = false, order: Int = 0) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = modifier
            .wrapContentSize()
            .padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            if (showOrder) {
                UxText(
                    text = order.toString(),
                    textType = TextType.LABEL_LARGE
                )
                Spacer(modifier = Modifier.width(4.dp))
                VerticalDivider(color = MaterialTheme.colorScheme.secondary)
                Spacer(modifier = Modifier.width(4.dp))
            }
            UxText(
                text = word,
                textType = TextType.LABEL_LARGE
            )
        }
    }
}
