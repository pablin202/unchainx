package com.pdm.unchainx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pdm.designsystems.component.button.UxButton
import com.pdm.designsystems.container.MessageType
import com.pdm.designsystems.container.UnchainXScreenContainer
import com.pdm.designsystems.theme.UnchainXTheme
import com.pdm.designsystems.utility.UiText

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
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            UxButton(
                text = "Show Error"
            ) {
                showError.value = true
            }
        }
    }
}
