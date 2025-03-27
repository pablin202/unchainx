package com.pdm.storage.database.di

import androidx.room.Room
import com.pdm.storage.database.WalletDatabase
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            get(),
            WalletDatabase::class.java,
            "wallet_db"
        )
            .build()
    }
    single { get<WalletDatabase>().walletDao() }
}
