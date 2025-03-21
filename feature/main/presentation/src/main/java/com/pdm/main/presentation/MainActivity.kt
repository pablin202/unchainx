package com.pdm.main.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.pdm.designsystems.container.MessageType
import com.pdm.designsystems.container.UnchainXScreenContainer
import com.pdm.designsystems.theme.UnchainXTheme
import com.pdm.designsystems.utility.UiText
import com.pdm.onboarding.presentation.intro.IntroductionScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnchainXTheme {
                WalletScreen()
            }
        }
    }
}

@Composable
fun WalletScreen() {
    val showError = remember { mutableStateOf(false) }

    UnchainXScreenContainer(
        showMessage = showError.value,
        message = UiText.SimpleString("Transaction failed!"),
        messageType = MessageType.Error
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding)
        ) {
            IntroductionScreen({})
        }
    }
}
