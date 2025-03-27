package com.pdm.wallet.domain.model

data class WalletEntity(
    val address: String,
    val path: String,
    val chain: DomainChainType,
    val isPrimary: Boolean = false
)