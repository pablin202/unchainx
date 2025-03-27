package com.pdm.wallet.presentation.verifyseed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pdm.designsystems.component.button.ButtonType
import com.pdm.designsystems.component.button.UxButton
import com.pdm.designsystems.component.preview.PreviewSurface
import com.pdm.designsystems.component.preview.UxPreview
import com.pdm.designsystems.component.text.TextType
import com.pdm.designsystems.component.text.UxText
import com.pdm.designsystems.theme.LocalDimensions
import com.pdm.designsystems.utility.UiText
import com.pdm.wallet.presentation.R
import com.pdm.wallet.presentation.components.SeedChip

@Composable
fun VerifySeedScreen(seed: List<String>, onVerificationSuccess: () -> Unit, onBack: () -> Unit) {
    VerifySeedContent(
        seed = seed,
        onVerificationSuccess = onVerificationSuccess,
        onBack = onBack
    )
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun VerifySeedContent(seed: List<String>, onVerificationSuccess: () -> Unit, onBack: () -> Unit) {
    val context = LocalContext.current

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    var selectedWords by remember { mutableStateOf(listOf<String>()) }
    var errorMessage by remember { mutableStateOf("") }
    val availableWords = remember(seed) { seed.shuffled() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalDimensions.current.dimensions16)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UxText(
                text = stringResource(R.string.verify_seed_title),
                textType = TextType.DISPLAY_MEDIUM,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

        UxText(
            text = stringResource(R.string.selected_word_label),
            textType = TextType.TITLE
        )

        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            selectedWords.forEachIndexed { index, word ->
                SeedChip(
                    word = word,
                    modifier = Modifier.clickable {
                        selectedWords = selectedWords.toMutableList().apply { removeAt(index) }
                        errorMessage = ""
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        UxText(
            text = stringResource(R.string.tab_word_in_order_label),
            textType = TextType.TITLE
        )
        Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            availableWords.forEach { word ->
                if (!selectedWords.contains(word)) {
                    SeedChip(
                        word = word,
                        modifier = Modifier.clickable {
                            selectedWords = selectedWords + word
                            errorMessage = ""
                            if (selectedWords.size == seed.size) {
                                if (selectedWords == seed) {
                                    showBottomSheet = true
                                } else {
                                    errorMessage =
                                        UiText.StringResource(R.string.seed_incorrect_error_message)
                                            .getString(context)
                                }
                            }
                        }
                    )
                }
            }
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            UxText(
                text = errorMessage,
                textType = TextType.LABEL_SMALL
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        UxButton(
            text = stringResource(R.string.reset),
            isFullWidth = true,
            buttonType = ButtonType.FILLED_TOTAL
        ) {
            selectedWords = emptyList()
            errorMessage = ""
        }

        Spacer(modifier = Modifier.height(8.dp))

        UxButton(
            text = stringResource(R.string.back),
            isFullWidth = true,
            buttonType = ButtonType.TEXT
        ) {
            onBack()
        }

        if (showBottomSheet) {
            PassphraseModalSheet(
                sheetState,
                scope,
                { passphrase ->
                    showBottomSheet = false
                    onVerificationSuccess()
                },
                {
                    showBottomSheet = false
                }
            )
        }
    }
}

@UxPreview
@Composable
fun VerifySeedScreenPreview() {
    PreviewSurface {
        VerifySeedContent(
            seed = listOf("word1", "word2", "word3"),
            onVerificationSuccess = {},
            onBack = {}
        )
    }
}
