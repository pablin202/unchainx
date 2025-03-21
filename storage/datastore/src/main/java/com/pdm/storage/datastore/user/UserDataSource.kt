package com.pdm.storage.datastore.user

import androidx.datastore.core.DataStore
import com.pdm.common.model.OnBoardingConfig
import com.pdm.common.model.ThemeConfig
import com.pdm.common.model.WalletBackupStatus
import com.pdm.storage.datastore.data.OnboardingStatus
import com.pdm.storage.datastore.data.Theme
import com.pdm.storage.datastore.data.UserPreferences
import com.pdm.storage.datastore.data.copy
import kotlinx.coroutines.flow.Flow

class UserDataSource(
    private val dataStore: DataStore<UserPreferences>
) {
    fun userData(): Flow<UserPreferences> {
        return dataStore.data
    }

    suspend fun setTheme(config: ThemeConfig) {
        dataStore.updateData {
            it.copy {
                theme = when (config) {
                    ThemeConfig.LIGHT -> Theme.LIGHT
                    ThemeConfig.DARK -> Theme.DARK
                    ThemeConfig.SYSTEM -> Theme.FOLLOW_SYSTEM
                }
            }
        }
    }

    suspend fun setOnboardingStatus(status: OnBoardingConfig) {
        dataStore.updateData {
            it.copy {
                onboardingStatus = when (status) {
                    OnBoardingConfig.COMPLETED -> OnboardingStatus.COMPLETED
                    OnBoardingConfig.NOT_STARTED -> OnboardingStatus.NOT_STARTED
                }
            }
        }
    }

    suspend fun setWalletBackupStatus(status: WalletBackupStatus) {
        dataStore.updateData {
            it.copy {
                walletBackupStatus = when (status) {
                    WalletBackupStatus.NOT_BACKED_UP -> com.pdm.storage.datastore.data.WalletBackupStatus.NOT_BACKED_UP
                    WalletBackupStatus.FULLY_BACKED_UP -> com.pdm.storage.datastore.data.WalletBackupStatus.FULLY_BACKED_UP
                    WalletBackupStatus.PARTIALLY_BACKED_UP -> com.pdm.storage.datastore.data.WalletBackupStatus.PARTIALLY_BACKED_UP
                }
            }
        }
    }

    suspend fun setDefaultCurrency(currency: String) {
        dataStore.updateData {
            it.copy {
                defaultCurrency = currency
            }
        }
    }

    suspend fun setNotificationEnabled(enabled: Boolean) {
        dataStore.updateData {
            it.copy {
                isNotificationsEnabled = enabled
            }
        }
    }

    suspend fun setWalletLock(enabled: Boolean) {
        dataStore.updateData {
            it.copy {
                isWalletUnlocked = enabled
            }
        }
    }

    suspend fun setTransactionAlertsEnabled(enabled: Boolean) {
        dataStore.updateData {
            it.copy {
                transactionAlertsEnabled = enabled
            }
        }
    }

    suspend fun setBiometricAuthEnabled(enabled: Boolean) {
        dataStore.updateData {
            it.copy {
                biometricAuthEnabled = enabled
            }
        }
    }

    suspend fun setFiatToCryptoConversionEnabled(enabled: Boolean) {
        dataStore.updateData {
            it.copy {
                selectedFiatToCryptoConversion = enabled
            }
        }
    }

    suspend fun setLastCopiedAddress(address: String) {
        dataStore.updateData {
            it.copy {
                lastCopiedAddress = address
            }
        }
    }

    suspend fun setLastSyncTimestamp(timestamp: Long) {
        dataStore.updateData {
            it.copy {
                lastSyncTimestamp = timestamp
            }
        }
    }

    suspend fun setHasUserCompletedSetup(completed: Boolean) {
        dataStore.updateData {
            it.copy {
                hasUserCompletedSetup = completed
            }
        }
    }
}
