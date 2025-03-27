package com.pdm.wallet.domain.usecase

import com.pdm.wallet.domain.repository.WalletRepository

class SaveSeedAndPassPhraseInSecureStore(
    private val walletRepository: WalletRepository
) {
    operator fun invoke(mnemonic: List<String>, passphrase: String?) {
        walletRepository.saveWalletSeed(mnemonic, passphrase)
    }
}