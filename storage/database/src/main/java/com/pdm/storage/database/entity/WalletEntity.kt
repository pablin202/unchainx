package com.pdm.storage.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallets")
data class WalletEntity(
    @PrimaryKey val address: String,
    val path: String,
    val chain: String,
    val isPrimary: Boolean = false
)