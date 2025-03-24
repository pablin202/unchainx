package com.pdm.main.domain

import com.pdm.common.model.UserPreferencesData
import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {
    fun getUserPreference(): Flow<UserPreferencesData>
}
