package com.pdm.wallet.presentation.di

import com.pdm.wallet.domain.usecase.DeriveWalletGroupsUseCase
import com.pdm.wallet.domain.usecase.GenerateMnemonicUseCase
import com.pdm.wallet.presentation.createwallet.mvi.CreateWalletViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val walletPresentationModule = module {
    factory { GenerateMnemonicUseCase(get()) }
    factory { DeriveWalletGroupsUseCase(get()) }
    viewModel {
        CreateWalletViewModel(get(), get())
    }
}
