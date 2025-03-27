package com.pdm.storage.preferences

import android.content.SharedPreferences
import androidx.core.content.edit

class SecureStorageDataSource(
    private val sharedPreferences: SharedPreferences
) {
    fun saveMnemonic(mnemonic: List<String>) {
        sharedPreferences.edit { putString("mnemonic", mnemonic.joinToString(" ")) }
    }

    fun getMnemonic(): List<String>? {
        return sharedPreferences.getString("mnemonic", null)?.split(" ")
    }

    fun savePassphrase(passphrase: String?) {
        sharedPreferences.edit { putString("passphrase", passphrase ?: "") }
    }

    fun getPassphrase(): String? {
        return sharedPreferences.getString("passphrase", "")?.takeIf { it.isNotBlank() }
    }

    fun clear() {
        sharedPreferences.edit { clear() }
    }
}