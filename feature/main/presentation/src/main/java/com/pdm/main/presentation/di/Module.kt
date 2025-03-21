package com.pdm.main.presentation.di

import com.pdm.onboarding.presentation.di.onboardingModule
import org.koin.dsl.module

val presentationModule = module {
    includes(onboardingModule)
}
