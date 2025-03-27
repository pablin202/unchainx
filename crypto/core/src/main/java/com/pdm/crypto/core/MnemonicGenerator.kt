package com.pdm.crypto.core

import org.bitcoinj.crypto.MnemonicCode
import java.security.SecureRandom

interface MnemonicGenerator {
    fun generateMnemonic(): List<String>
}

class BIP39MnemonicGenerator : MnemonicGenerator {
    override fun generateMnemonic(): List<String> {
        try {
            val entropySize = 16
            val secureRandom = SecureRandom()
            val entropy = ByteArray(entropySize)
            secureRandom.nextBytes(entropy)
            return MnemonicCode.INSTANCE.toMnemonic(entropy)
        } catch (e: Exception) {
            throw e
        }
    }
}
