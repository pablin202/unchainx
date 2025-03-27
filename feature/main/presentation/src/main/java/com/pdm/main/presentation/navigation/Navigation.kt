package com.pdm.main.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.pdm.onboarding.presentation.intro.IntroductionScreen
import com.pdm.wallet.presentation.createwallet.CreateWalletScreen
import com.pdm.wallet.presentation.home.HomeScreen
import com.pdm.wallet.presentation.started.StartScreen
import com.pdm.wallet.presentation.verifyseed.VerifySeedScreen

fun NavGraphBuilder.navigateToOnBoarding(navController: NavController) {
    navigation<Graphs.OnBoarding>(
        startDestination = Destinations.OnBoarding
    ) {
        uxComposable<Destinations.OnBoarding> {
            IntroductionScreen({
                navController.navigate(Graphs.Start)
            })
        }
    }
}

fun NavGraphBuilder.navigateToStart(navController: NavController) {
    navigation<Graphs.Start>(
        startDestination = Destinations.Start
    ) {
        uxComposable<Destinations.Start> {
            StartScreen({
                navController.navigate(Destinations.CreateWallet)
            }, {})
        }

        uxComposable<Destinations.CreateWallet> {
            CreateWalletScreen { seed ->
                navController.navigate(Destinations.VerifySeed(seed))
            }
        }

        uxComposable<Destinations.VerifySeed> { backStackEntry ->
            val seedString = backStackEntry.arguments?.getStringArray("seed")?.toList() ?: emptyList()
            VerifySeedScreen(
                seed = seedString,
                onVerificationSuccess = {
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

fun NavGraphBuilder.navigateToMain(navController: NavController) {
    navigation<Graphs.Main>(
        startDestination = Destinations.Home
    ) {
        uxComposable<Destinations.Home> {
            HomeScreen()
        }
    }
}
