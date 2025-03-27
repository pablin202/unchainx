package com.pdm.wallet.presentation.createwallet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pdm.designsystems.component.button.ButtonType
import com.pdm.designsystems.component.button.UxButton
import com.pdm.designsystems.component.text.TextType
import com.pdm.designsystems.component.text.UxText
import com.pdm.designsystems.theme.LocalDimensions
import com.pdm.wallet.presentation.R
import com.pdm.wallet.presentation.components.SeedChip
import com.pdm.wallet.presentation.createwallet.mvi.CreateWalletViewModel
import com.pdm.wallet.presentation.createwallet.mvi.Event
import com.pdm.wallet.presentation.createwallet.mvi.UiState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CreateWalletScreen(
    viewModel: CreateWalletViewModel = koinViewModel<CreateWalletViewModel>(),
    onNavigateToVerification: (List<String>) -> Unit
) {
    val context = LocalContext.current

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.event(Event.CreateSeed)
    }

    CreateWalletContent(
        state,
        {
            onNavigateToVerification.invoke(
                it
            )
        },
        {
            viewModel.event(Event.CopySeedToClipBoard(context))
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateWalletContent(state: UiState, onVerifySeed: (List<String>) -> Unit = {}, onCopySeed: () -> Unit = {}) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalDimensions.current.dimensions16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            UiState.Loading, UiState.Idle -> CircularProgressIndicator()
            is UiState.Error -> {
                UxText(text = state.message.getString(context), textType = TextType.DISPLAY_MEDIUM)
            }

            is UiState.Success -> {
                val seedPhrase = state.seedPhrase
                UxText(
                    text = stringResource(R.string.your_recovery_phase_title),
                    textType = TextType.DISPLAY_MEDIUM
                )

                Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

                UxText(
                    text = stringResource(R.string.your_recovery_phase_subtitle),
                    textType = TextType.TITLE,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    seedPhrase.forEachIndexed { index, word ->
                        SeedChip(
                            word = word,
                            showOrder = true,
                            order = index + 1
                        )
                    }
                }

                Spacer(modifier = Modifier.height(LocalDimensions.current.dimensions16))

                UxButton(
                    text = stringResource(R.string.copy_to_clipboard),
                    isFullWidth = true,
                    buttonType = ButtonType.TEXT
                ) {
                    onCopySeed.invoke()
                }

                Spacer(modifier = Modifier.weight(1f))

                UxButton(
                    text = stringResource(R.string.verify_wallet),
                    isFullWidth = true,
                    buttonType = ButtonType.FILLED_TOTAL
                ) {
                    onVerifySeed.invoke(state.seedPhrase)
                }
            }
        }
    }
}
