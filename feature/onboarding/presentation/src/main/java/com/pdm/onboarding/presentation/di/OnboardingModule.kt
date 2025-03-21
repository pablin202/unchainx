package com.pdm.onboarding.presentation.di

import com.pdm.onboarding.presentation.mvi.OnBoardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val onboardingModule = module {
    viewModel {
        OnBoardingViewModel(get())
    }
}
