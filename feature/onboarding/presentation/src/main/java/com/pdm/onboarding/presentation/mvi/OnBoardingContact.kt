package com.pdm.onboarding.presentation.mvi

import com.pdm.designsystems.utility.UiText

sealed interface Event {
    data object OnboardingCompleted : Event
}

sealed interface Effects {
    data object NavigateToHome : Effects
    data class ShowError(val message: UiText) : Effects
}
