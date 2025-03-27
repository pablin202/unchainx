package com.pdm.wallet.domain.model

data class ChainConfig(
    val path: String,
    val chain: DomainChainType,
    val addressCount: Int
)