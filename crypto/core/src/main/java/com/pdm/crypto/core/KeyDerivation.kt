package com.pdm.crypto.core

import org.bitcoinj.crypto.HDKeyDerivation
import org.bitcoinj.crypto.ChildNumber
import org.bitcoinj.crypto.DeterministicKey

class KeyDerivation {
    fun deriveMasterKey(seed: ByteArray): DeterministicKey {
        return HDKeyDerivation.createMasterPrivateKey(seed)
    }

    fun deriveKeyFromPath(masterKey: DeterministicKey, path: String): DeterministicKey {
        val segments = path.split("/")
            .filter { it.isNotEmpty() && it != "m" }
            .map { segment ->
                when {
                    segment.endsWith("'") -> ChildNumber(segment.dropLast(1).toInt(), true)
                    else -> ChildNumber(segment.toInt(), false)
                }
            }

        var key = masterKey
        for (childNumber in segments) {
            key = HDKeyDerivation.deriveChildKey(key, childNumber)
        }
        return key
    }
}