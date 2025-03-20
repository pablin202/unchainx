package com.pdm.designsystems.container

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MessageBanner(message: String, messageType: MessageType) {
    val backgroundColor = when (messageType) {
        MessageType.Error -> MaterialTheme.colorScheme.errorContainer
        MessageType.Success -> Color(0xFF4BDC9E)
        MessageType.Warning -> Color(0xFFFFC107)
        MessageType.Info -> MaterialTheme.colorScheme.primary
    }

    val textColor = when (messageType) {
        MessageType.Error -> MaterialTheme.colorScheme.onErrorContainer
        MessageType.Success, MessageType.Warning, MessageType.Info -> Color.Black
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor.copy(alpha = 0.9f))
            .border(2.dp, backgroundColor, RoundedCornerShape(12.dp))
            .shadow(8.dp, shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Text(
            text = message,
            color = textColor,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
