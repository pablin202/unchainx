package com.pdm.wallet.domain.providers

import com.pdm.wallet.domain.model.ChainConfig

interface WalletChainConfigProvider {
    fun getSupportedChains(): List<ChainConfig>
}