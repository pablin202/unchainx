package com.pdm.onboarding.presentation.dummy

import com.pdm.common.model.OnBoardingConfig
import com.pdm.common.model.UserPreferencesData
import com.pdm.onboarding.domain.UserPreferenceRepository

class DummyUserPreferenceRepository : UserPreferenceRepository {
    private val dummyPreferences = mutableListOf<UserPreferencesData>()
    override suspend fun saveUserOnboardingStatus(onboardingStatus: OnBoardingConfig) {
        TODO("Not yet implemented")
    }
}
