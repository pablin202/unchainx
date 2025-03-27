package com.pdm.wallet.domain.model

data class DomainDerivedWallet(
    val privateKey: ByteArray,
    val publicKey: ByteArray,
    val address: String,
    val path: String,
    val chain: DomainChainType
)