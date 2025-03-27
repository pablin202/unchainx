package com.pdm.wallet.data

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.pdm.common.model.AppError
import com.pdm.crypto.core.KeyDerivation
import com.pdm.crypto.core.MnemonicGenerator
import com.pdm.crypto.core.MnemonicUtils
import com.pdm.crypto.core.WalletKeyDerivationService
import com.pdm.crypto.core.model.ChainType
import com.pdm.wallet.domain.model.DerivedWalletGroup
import com.pdm.wallet.domain.model.DomainChainType
import com.pdm.wallet.domain.model.DomainDerivedWallet
import com.pdm.wallet.domain.model.WalletDerivationInput
import com.pdm.wallet.domain.providers.WalletChainConfigProvider
import com.pdm.wallet.domain.repository.CryptoRepository

class CryptoRepositoryImpl(
    private val mnemonicGenerator: MnemonicGenerator,
    private val mnemonicUtils: MnemonicUtils,
    private val keyDerivation: KeyDerivation,
    private val configProvider: WalletChainConfigProvider,
    private val walletKeyDerivationService: WalletKeyDerivationService
) : CryptoRepository {

    override fun getNewMnemonic(): Either<AppError, List<String>> {
        return try {
            val seedPhrase = mnemonicGenerator.generateMnemonic()
            if (seedPhrase.isEmpty()) {
                AppError.CryptoError.KeyGenerationError("The seed phrase is empty").left()
            } else {
                seedPhrase.right()
            }
        } catch (e: Exception) {
            AppError.UnknownError("Error generating the wallet", e).left()
        }
    }

    override suspend fun deriveAllWalletGroups(input: WalletDerivationInput): List<DerivedWalletGroup> {
        val seed = mnemonicUtils.generateSeed(input.mnemonic, input.passphrase)
        val masterKey = keyDerivation.deriveMasterKey(seed)
        return configProvider.getSupportedChains().map { config ->
            val wallets = (0 until config.addressCount).map { index ->
                val fullPath = "${config.path}/$index"
                when (config.chain) {
                    DomainChainType.ETHEREUM -> {
                        walletKeyDerivationService.deriveEthereumWallet(masterKey, fullPath)
                    }
                    DomainChainType.BITCOIN -> {
                        walletKeyDerivationService.deriveBitcoinWallet(masterKey, fullPath)
                    }
                    else -> throw IllegalArgumentException("Unsupported chain type: ${config.chain}")
                }
            }
            DerivedWalletGroup(config.chain, wallets.map {
                DomainDerivedWallet(
                    privateKey = it.privateKey,
                    publicKey = it.publicKey,
                    address = it.address,
                    path = it.path,
                    chain = when (it.chain) {
                        ChainType.ETHEREUM -> DomainChainType.ETHEREUM
                        ChainType.BITCOIN -> DomainChainType.BITCOIN
                    }
                )
            })
        }
    }
}
