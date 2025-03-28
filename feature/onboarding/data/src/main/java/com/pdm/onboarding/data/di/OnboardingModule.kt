package com.pdm.onboarding.data.di

import com.pdm.onboarding.data.repository.UserPreferenceRepositoryImpl
import com.pdm.onboarding.domain.UserPreferenceRepository
import com.pdm.storage.datastore.di.dataStorePreferenceModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val onboardingDataModule = module {
    includes(dataStorePreferenceModule)
    singleOf(::UserPreferenceRepositoryImpl) bind UserPreferenceRepository::class
}
