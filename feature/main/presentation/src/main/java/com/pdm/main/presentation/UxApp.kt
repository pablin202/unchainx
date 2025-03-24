package com.pdm.main.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pdm.common.model.OnBoardingConfig
import com.pdm.common.model.ThemeConfig
import com.pdm.common.model.UserPreferencesData
import com.pdm.designsystems.container.MessageType
import com.pdm.designsystems.container.UnchainXScreenContainer
import com.pdm.designsystems.theme.UnchainXTheme
import com.pdm.designsystems.utility.UiText
import com.pdm.main.presentation.mvi.UiState
import com.pdm.main.presentation.navigation.Graphs
import com.pdm.main.presentation.navigation.navigateToMain
import com.pdm.main.presentation.navigation.navigateToOnBoarding
import com.pdm.main.presentation.navigation.navigateToStart
import kotlinx.coroutines.delay

@Composable
fun UxApp(uiState: UiState.Success) {
    val navController = rememberNavController()
    var showErrorMessage by remember { mutableStateOf(false) }
    val errorMessage by remember { mutableStateOf<UiText>(UiText.Empty) }
    val errorType by remember { mutableStateOf<MessageType>(MessageType.Error) }

    LaunchedEffect(showErrorMessage) {
        if (showErrorMessage) {
            delay(3000)
            showErrorMessage = false
        }
    }

    UnchainXTheme(darkTheme = isDarkTheme(uiState)) {
        UnchainXScreenContainer(
            showMessage = showErrorMessage,
            message = errorMessage,
            messageType = errorType
        ) { padding ->
            Box(
                modifier = Modifier.padding(padding)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = getDestinations(uiState.data)
                ) {
                    navigateToOnBoarding(navController)
                    navigateToStart(navController)
                    navigateToMain(navController)
                }
            }
        }
    }
}

private fun getDestinations(preferences: UserPreferencesData) =
    when (preferences.onboardingStatus) {
        OnBoardingConfig.NOT_STARTED -> Graphs.OnBoarding
        else -> {
            if (preferences.hasUserCompletedSetup) {
                Graphs.Main
            } else {
                Graphs.Start
            }
        }
    }

@Composable
private fun isDarkTheme(uiState: UiState): Boolean {
    return when (uiState) {
        UiState.Loading -> false
        is UiState.Success -> when (uiState.data.theme) {
            ThemeConfig.DARK -> true
            ThemeConfig.SYSTEM -> isSystemInDarkTheme()
            ThemeConfig.LIGHT -> false
        }
    }
}
