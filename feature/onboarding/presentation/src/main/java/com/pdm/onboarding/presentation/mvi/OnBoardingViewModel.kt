package com.pdm.onboarding.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.common.model.OnBoardingConfig
import com.pdm.onboarding.domain.UserPreferenceRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val preferenceRepository: UserPreferenceRepository
) : ViewModel() {

    private val mutableSharedFlow: MutableSharedFlow<Effects> = MutableSharedFlow()
    val effect: SharedFlow<Effects>
        get() = mutableSharedFlow.asSharedFlow()

    fun event(event: Event) {
        when (event) {
            Event.OnboardingCompleted -> {
                viewModelScope.launch {
                    preferenceRepository.saveUserOnboardingStatus(
                        OnBoardingConfig.COMPLETED
                    )
                }
            }
        }
    }
}
