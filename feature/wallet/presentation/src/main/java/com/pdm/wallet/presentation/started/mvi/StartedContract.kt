package com.pdm.wallet.presentation.started.mvi

import com.pdm.designsystems.utility.UiText

sealed interface Event {
    data object CreateWallet : Event
    data object ImportWallet : Event
}

sealed interface Effects {
    data object NavigateToHome : Effects
    data class ShowError(val message: UiText) : Effects
}
