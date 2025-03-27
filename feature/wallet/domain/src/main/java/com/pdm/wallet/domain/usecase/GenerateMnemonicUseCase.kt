package com.pdm.wallet.domain.usecase

import arrow.core.Either
import com.pdm.common.model.AppError
import com.pdm.wallet.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenerateMnemonicUseCase(
    private val cryptoRepository: CryptoRepository
) {
    operator fun invoke(): Flow<Either<AppError, List<String>>> =
        flow {
            emit(cryptoRepository.getNewMnemonic())
        }
}
