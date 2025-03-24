package com.pdm.main.data.repository

import com.pdm.common.model.OnBoardingConfig
import com.pdm.common.model.ThemeConfig
import com.pdm.common.model.UserPreferencesData
import com.pdm.main.domain.UserPreferenceRepository
import com.pdm.storage.datastore.data.OnboardingStatus
import com.pdm.storage.datastore.data.Theme
import com.pdm.storage.datastore.data.WalletBackupStatus
import com.pdm.storage.datastore.user.UserDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class UserPreferenceRepoImpl(
    private val source: UserDataSource
) : UserPreferenceRepository {
    override fun getUserPreference(): Flow<UserPreferencesData> =
        flow {
            emit(
                source.userData().first().let {
                    UserPreferencesData(
                        onboardingStatus = when (it.onboardingStatus) {
                            OnboardingStatus.COMPLETED -> OnBoardingConfig.COMPLETED
                            OnboardingStatus.NOT_STARTED -> OnBoardingConfig.NOT_STARTED
                            OnboardingStatus.UNRECOGNIZED, null -> OnBoardingConfig.NOT_STARTED
                        },
                        theme = when (it.theme) {
                            Theme.DARK -> ThemeConfig.DARK
                            Theme.LIGHT -> ThemeConfig.LIGHT
                            Theme.FOLLOW_SYSTEM -> ThemeConfig.SYSTEM
                            Theme.UNRECOGNIZED, null -> ThemeConfig.SYSTEM
                        },
                        walletBackupStatus = when (it.walletBackupStatus) {
                            WalletBackupStatus.NOT_BACKED_UP ->
                                com.pdm.common.model.WalletBackupStatus.NOT_BACKED_UP

                            WalletBackupStatus.FULLY_BACKED_UP ->
                                com.pdm.common.model.WalletBackupStatus.FULLY_BACKED_UP

                            WalletBackupStatus.PARTIALLY_BACKED_UP,
                            WalletBackupStatus.UNRECOGNIZED, null ->
                                com.pdm.common.model.WalletBackupStatus.PARTIALLY_BACKED_UP
                        },
                        isNotificationsEnabled = it.isNotificationsEnabled,
                        defaultCurrency = it.defaultCurrency,
                        transactionAlertsEnabled = it.transactionAlertsEnabled,
                        isWalletUnlocked = it.isWalletUnlocked,
                        hasUserCompletedSetup = it.hasUserCompletedSetup,
                        biometricAuthEnabled = it.biometricAuthEnabled,
                        selectedFiatToCryptoConversion = it.selectedFiatToCryptoConversion,
                        lastCopiedAddress = it.lastCopiedAddress,
                        lastSyncTimestamp = it.lastSyncTimestamp
                    )
                }
            )
        }
}
