package com.pdm.unchainx.di

import com.pdm.main.data.di.mainDataModule
import com.pdm.main.presentation.di.presentationModule
import com.pdm.onboarding.data.di.onboardingDataModule
import com.pdm.wallet.data.di.walletDataModule
import org.koin.dsl.module

val appModule = module {
    includes(mainDataModule)
    includes(onboardingDataModule)
    includes(walletDataModule)
    includes(presentationModule)
}
