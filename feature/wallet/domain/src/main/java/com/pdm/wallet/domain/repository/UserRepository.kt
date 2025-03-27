package com.pdm.wallet.domain.repository

interface UserRepository {
    suspend fun saveUserSetup()
}
