package com.pdm.wallet.presentation.createwallet.mvi

import android.content.Context
import com.pdm.designsystems.utility.UiText

sealed interface UiState {
    data object Idle : UiState
    data object Loading : UiState
    data class Error(val message: UiText) : UiState
    data class Success(val seedPhrase: List<String>) : UiState
}

sealed interface Event {
    data object CreateWallet : Event
    data class CopySeedToClipBoard(val context: Context) : Event
}

sealed interface Effects {
    data class ShowError(val message: UiText) : Effects
    data class ShowMessage(val message: UiText) : Effects
}
