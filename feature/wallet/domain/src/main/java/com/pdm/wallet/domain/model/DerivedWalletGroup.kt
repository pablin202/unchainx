package com.pdm.wallet.domain.model

data class DerivedWalletGroup(
    val chain: DomainChainType,
    val wallets: List<DomainDerivedWallet>
)