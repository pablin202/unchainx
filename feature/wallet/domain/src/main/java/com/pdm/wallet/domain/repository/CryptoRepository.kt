package com.pdm.wallet.domain.repository

import arrow.core.Either
import com.pdm.common.model.AppError
import com.pdm.wallet.domain.model.DerivedWalletGroup
import com.pdm.wallet.domain.model.WalletDerivationInput

interface CryptoRepository {
    fun getNewMnemonic(): Either<AppError, List<String>>
    suspend fun deriveAllWalletGroups(input: WalletDerivationInput): List<DerivedWalletGroup>
}
