package com.pdm.wallet.domain.model

data class WalletDerivationInput(
    val mnemonic: List<String>,
    val passphrase: String? = null
)
