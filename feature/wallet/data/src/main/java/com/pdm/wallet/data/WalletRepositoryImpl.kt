package com.pdm.wallet.data

import com.pdm.wallet.domain.repository.WalletRepository
import com.pdm.storage.preferences.SecureStorageDataSource

class WalletRepositoryImpl(
    private val secureStorage: SecureStorageDataSource
) : WalletRepository {

    override fun saveWalletSeed(mnemonic: List<String>, passphrase: String?) {
        secureStorage.saveMnemonic(mnemonic)
        secureStorage.savePassphrase(passphrase)
    }

    override fun loadWalletSeed(): Pair<List<String>, String?>? {
        val mnemonic = secureStorage.getMnemonic() ?: return null
        val passphrase = secureStorage.getPassphrase()
        return mnemonic to passphrase
    }
}
