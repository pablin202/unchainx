package com.pdm.wallet.presentation.verifyseed.mvi

import com.pdm.designsystems.utility.UiText

sealed interface UiState {
    data object Idle : UiState
    data object Loading : UiState
}

sealed interface Event {
    data class GetDerivedWallet(val mnemonic: List<String>, val passphrase: String?) : Event
}

sealed interface Effects {
    data object GoToHome : Effects
    data class ShowMessage(val message: UiText) : Effects
}