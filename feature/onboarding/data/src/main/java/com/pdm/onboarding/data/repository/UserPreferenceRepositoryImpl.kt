package com.pdm.onboarding.data.repository

import com.pdm.common.model.OnBoardingConfig
import com.pdm.onboarding.domain.UserPreferenceRepository
import com.pdm.storage.datastore.user.UserDataSource

class UserPreferenceRepositoryImpl(
    private val source: UserDataSource
) : UserPreferenceRepository {
    override suspend fun saveUserOnboardingStatus(onboardingStatus: OnBoardingConfig) {
        source.setOnboardingStatus(onboardingStatus)
    }
}
