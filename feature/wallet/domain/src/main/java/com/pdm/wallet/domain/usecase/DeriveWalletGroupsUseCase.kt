package com.pdm.wallet.domain.usecase

import com.pdm.wallet.domain.model.DerivedWalletGroup
import com.pdm.wallet.domain.model.WalletDerivationInput
import com.pdm.wallet.domain.repository.CryptoRepository

class DeriveWalletGroupsUseCase(
    private val cryptoRepository: CryptoRepository
) {
    suspend operator fun invoke(
        mnemonic: List<String>,
        passphrase: String?
    ): List<DerivedWalletGroup> {
        return cryptoRepository.deriveAllWalletGroups(
            WalletDerivationInput(
                mnemonic = mnemonic,
                passphrase = passphrase
            )
        )
    }
}
