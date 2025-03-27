package com.pdm.crypto.core.model

data class DerivedWallet(
    val privateKey: ByteArray,
    val publicKey: ByteArray,
    val address: String,
    val path: String,
    val chain: ChainType
)