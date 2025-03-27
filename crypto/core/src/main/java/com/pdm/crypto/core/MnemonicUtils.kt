package com.pdm.crypto.core

import org.bitcoinj.crypto.MnemonicCode

class MnemonicUtils {
    fun generateSeed(mnemonic: List<String>, passphrase: String?): ByteArray {
        return MnemonicCode.toSeed(mnemonic, passphrase ?: "")
    }
}