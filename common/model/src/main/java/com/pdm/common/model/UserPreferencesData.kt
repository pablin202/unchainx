package com.pdm.common.model

data class UserPreferencesData(
    val theme: ThemeConfig = ThemeConfig.SYSTEM,
    val onboardingStatus: OnBoardingConfig = OnBoardingConfig.NOT_STARTED,
    val walletBackupStatus: WalletBackupStatus = WalletBackupStatus.NOT_BACKED_UP,
    val isWalletUnlocked: Boolean = false,
    val hasUserCompletedSetup: Boolean = false,
    val biometricAuthEnabled: Boolean = false,
    val defaultCurrency: String = "USD",
    val selectedFiatToCryptoConversion: Boolean = false,
    val lastCopiedAddress: String? = null,
    val isNotificationsEnabled: Boolean = true,
    val transactionAlertsEnabled: Boolean = true,
    val lastSyncTimestamp: Long = 0L
)
