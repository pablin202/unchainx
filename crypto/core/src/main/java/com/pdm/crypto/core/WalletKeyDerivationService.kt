package com.pdm.crypto.core

import com.pdm.crypto.core.model.ChainType
import com.pdm.crypto.core.model.DerivedWallet
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Keys
import org.bitcoinj.core.ECKey
import org.bitcoinj.core.LegacyAddress
import org.bitcoinj.crypto.DeterministicKey
import org.bitcoinj.params.MainNetParams

class WalletKeyDerivationService(
    private val keyDerivation: KeyDerivation
) {
    fun deriveEthereumWallet(
        masterKey: DeterministicKey,
        fullPath: String
    ): DerivedWallet {
        val derivedKey = keyDerivation.deriveKeyFromPath(masterKey, fullPath)
        val ecKeyPair = ECKeyPair.create(derivedKey.privKeyBytes)
        val publicKeyBytes = ecKeyPair.publicKey.toByteArray()
        val address = "0x" + Keys.getAddress(ecKeyPair)
        return DerivedWallet(
            privateKey = derivedKey.privKeyBytes,
            publicKey = publicKeyBytes,
            address = address,
            path = fullPath,
            chain = ChainType.ETHEREUM
        )
    }

    fun deriveBitcoinWallet(
        masterKey: DeterministicKey,
        fullPath: String
    ): DerivedWallet {
        val derivedKey = keyDerivation.deriveKeyFromPath(masterKey, fullPath)
        val ecKey = ECKey.fromPrivate(derivedKey.privKey)
        val address = LegacyAddress.fromKey(MainNetParams.get(), ecKey).toString()
        val publicKeyBytes = ecKey.pubKey
        return DerivedWallet(
            privateKey = ecKey.privKeyBytes,
            publicKey = publicKeyBytes,
            address = address,
            path = fullPath,
            chain = ChainType.BITCOIN
        )
    }
}
