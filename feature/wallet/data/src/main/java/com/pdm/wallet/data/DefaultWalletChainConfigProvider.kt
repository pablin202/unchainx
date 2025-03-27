package com.pdm.wallet.data

import com.pdm.wallet.domain.model.DomainChainType
import com.pdm.wallet.domain.providers.WalletChainConfigProvider

class DefaultWalletChainConfigProvider : WalletChainConfigProvider {
    override fun getSupportedChains(): List<com.pdm.wallet.domain.model.ChainConfig> = listOf(
        com.pdm.wallet.domain.model.ChainConfig("m/44'/60'/0'/0", DomainChainType.ETHEREUM, 3),
        com.pdm.wallet.domain.model.ChainConfig("m/44'/0'/0'/0", DomainChainType.BITCOIN, 3),
        com.pdm.wallet.domain.model.ChainConfig("m/44'/137'/0'/0", DomainChainType.ETHEREUM, 3),
        com.pdm.wallet.domain.model.ChainConfig("m/44'/714'/0'/0", DomainChainType.ETHEREUM, 3)
    )
}
