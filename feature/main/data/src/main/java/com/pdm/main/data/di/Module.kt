package com.pdm.main.data.di

import com.pdm.main.data.repository.UserPreferenceRepoImpl
import com.pdm.main.domain.UserPreferenceRepository
import com.pdm.storage.datastore.di.dataStorePreferenceModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mainDataModule = module {
    includes(dataStorePreferenceModule)
    singleOf(::UserPreferenceRepoImpl) bind UserPreferenceRepository::class
}
