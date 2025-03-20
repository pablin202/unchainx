package com.pdm.designsystems.container

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.pdm.designsystems.R
import com.pdm.designsystems.utility.UiText
import kotlinx.coroutines.delay

@Composable
fun UnchainXScreenContainer(
    modifier: Modifier = Modifier,
    showMessage: Boolean = false,
    message: UiText = UiText.StringResource(R.string.default_message),
    messageType: MessageType = MessageType.Info, // Permite mensajes de error, éxito o advertencia
    content: @Composable () -> Unit
) {
    var showMessageState by remember { mutableStateOf(showMessage) }

    LaunchedEffect(showMessage) {
        showMessageState = showMessage
        if (showMessage) {
            delay(3000)
            showMessageState = false
        }
    }

    UnchainXSurface(modifier = modifier.fillMaxSize()) {
        Scaffold { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                content()
                AnimatedVisibility(
                    visible = showMessageState,
                    enter = fadeIn() + slideInVertically(initialOffsetY = { -40 }),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    MessageBanner(
                        message = message.getString(LocalContext.current),
                        messageType = messageType
                    )
                }
            }
        }
    }
}

