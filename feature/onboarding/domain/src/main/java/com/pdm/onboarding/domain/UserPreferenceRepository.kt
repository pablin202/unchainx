package com.pdm.onboarding.domain

import com.pdm.common.model.OnBoardingConfig

interface UserPreferenceRepository {
    suspend fun saveUserOnboardingStatus(onboardingStatus: OnBoardingConfig)
}
