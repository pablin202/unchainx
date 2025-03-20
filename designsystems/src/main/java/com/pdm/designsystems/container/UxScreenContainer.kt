package com.pdm.designsystems.container

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.pdm.designsystems.R
import com.pdm.designsystems.utility.UiText
import kotlinx.coroutines.delay

@Composable
fun UnchainXScreenContainer(
    modifier: Modifier = Modifier,
    withGradient: Boolean = true,
    topAppBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    showMessage: Boolean = false,
    message: UiText = UiText.StringResource(R.string.default_message),
    messageType: MessageType = MessageType.Info,
    content: @Composable (PaddingValues) -> Unit
) {
    var showMessageState by remember { mutableStateOf(showMessage) }

    LaunchedEffect(showMessage) {
        showMessageState = showMessage
        if (showMessage) {
            delay(3000)
            showMessageState = false
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Scaffold(
            topBar = topAppBar,
            floatingActionButton = floatingActionButton,
            floatingActionButtonPosition = FabPosition.Center,
            modifier = modifier
        ) { padding ->
            if (withGradient) {
                UnchainXGradientBackground {
                    content(padding)
                }
            } else {
                content(padding)
            }
        }

        AnimatedVisibility(
            visible = showMessageState,
            enter = fadeIn() + slideInVertically(initialOffsetY = { -40 }),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 56.dp)
        ) {
            MessageBanner(
                message = message.getString(LocalContext.current),
                messageType = messageType
            )
        }
    }
}
