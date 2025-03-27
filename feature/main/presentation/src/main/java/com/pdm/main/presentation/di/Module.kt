package com.pdm.main.presentation.di

import com.pdm.main.presentation.mvi.MainViewModel
import com.pdm.onboarding.presentation.di.onboardingModule
import com.pdm.wallet.presentation.di.walletPresentationModule
import org.koin.dsl.module

val presentationModule = module {
    includes(onboardingModule)
    includes(walletPresentationModule)
    single {
        MainViewModel(get())
    }
}
