package com.pdm.main.presentation.mvi

import com.pdm.common.model.UserPreferencesData

sealed interface UiState {
    data object Loading : UiState
    data class Success(val data: UserPreferencesData) : UiState
}
