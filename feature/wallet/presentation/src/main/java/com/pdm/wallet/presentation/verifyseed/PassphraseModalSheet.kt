package com.pdm.wallet.presentation.verifyseed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.pdm.designsystems.component.button.ButtonType
import com.pdm.designsystems.component.button.UxButton
import com.pdm.designsystems.component.text.TextType
import com.pdm.designsystems.component.text.UxText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassphraseModalSheet(sheetState: SheetState, scope: CoroutineScope, onConfirm: (String) -> Unit, onDismiss: () -> Unit) {
    var passphrase by remember { mutableStateOf("") }
    var confirmPassphrase by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            UxText(
                text = "Enter Passphrase",
                textType = TextType.DISPLAY_MEDIUM
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = passphrase,
                onValueChange = { passphrase = it },
                label = { UxText(text = "Passphrase", textType = TextType.LABEL_SMALL) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = confirmPassphrase,
                onValueChange = { confirmPassphrase = it },
                label = { UxText(text = "Confirm Passphrase", textType = TextType.LABEL_SMALL) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            if (errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                UxText(
                    text = errorMessage,
                    textType = TextType.LABEL_SMALL
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            UxButton(
                text = "Confirm",
                isFullWidth = true,
                buttonType = ButtonType.FILLED_TOTAL,
                onClick = {
                    if (passphrase.isNotBlank() && passphrase == confirmPassphrase) {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onConfirm(passphrase)
                            }
                        }
                    } else {
                        errorMessage = "Passphrases do not match or are empty."
                    }
                }
            )
        }
    }
}
