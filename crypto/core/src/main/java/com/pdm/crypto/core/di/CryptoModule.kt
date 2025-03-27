package com.pdm.crypto.core.di

import com.pdm.crypto.core.BIP39MnemonicGenerator
import com.pdm.crypto.core.KeyDerivation
import com.pdm.crypto.core.MnemonicGenerator
import com.pdm.crypto.core.MnemonicUtils
import org.koin.dsl.module

val cryptoModule = module {
    factory<MnemonicGenerator> { BIP39MnemonicGenerator() }
    single { MnemonicUtils() }
    single { KeyDerivation() }
    single { com.pdm.crypto.core.WalletKeyDerivationService(get()) }
}
