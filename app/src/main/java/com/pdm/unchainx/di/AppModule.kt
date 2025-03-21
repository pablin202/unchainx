package com.pdm.unchainx.di

import com.pdm.main.presentation.di.presentationModule
import com.pdm.onboarding.data.di.onboardingDataModule
import org.koin.dsl.module

val appModule = module {
    includes(onboardingDataModule)
    includes(presentationModule)
}
