package com.pdm.wallet.domain.repository

interface WalletRepository {
    fun saveWalletSeed(mnemonic: List<String>, passphrase: String?)
    fun loadWalletSeed(): Pair<List<String>, String?>?
}
