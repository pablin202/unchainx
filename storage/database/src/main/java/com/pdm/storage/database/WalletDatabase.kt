package com.pdm.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pdm.storage.database.dao.WalletDao
import com.pdm.storage.database.entity.WalletEntity

@Database(entities = [WalletEntity::class], version = 1)
abstract class WalletDatabase : RoomDatabase() {
    abstract fun walletDao(): WalletDao
}