package com.pdm.wallet.data.di

import com.pdm.crypto.core.di.cryptoModule
import com.pdm.storage.datastore.di.dataStorePreferenceModule
import com.pdm.storage.preferences.di.secureStorageModule
import com.pdm.wallet.data.CryptoRepositoryImpl
import com.pdm.wallet.data.DefaultWalletChainConfigProvider
import com.pdm.wallet.data.UserRepositoryImpl
import com.pdm.wallet.data.WalletRepositoryImpl
import com.pdm.wallet.domain.providers.WalletChainConfigProvider
import com.pdm.wallet.domain.repository.CryptoRepository
import com.pdm.wallet.domain.repository.UserRepository
import com.pdm.wallet.domain.repository.WalletRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val walletDataModule = module {
    includes(dataStorePreferenceModule)
    includes(cryptoModule)
    includes(secureStorageModule)
    singleOf(::CryptoRepositoryImpl) bind CryptoRepository::class
    singleOf(::UserRepositoryImpl) bind UserRepository::class
    singleOf(::DefaultWalletChainConfigProvider) bind WalletChainConfigProvider::class
    singleOf(::WalletRepositoryImpl) bind WalletRepository::class
}
