package com.pdm.wallet.data

import com.pdm.storage.datastore.user.UserDataSource
import com.pdm.wallet.domain.repository.UserRepository

class UserRepositoryImpl(
    private val source: UserDataSource
) : UserRepository {
    override suspend fun saveUserSetup() {
        source.setHasUserCompletedSetup(true)
    }
}
