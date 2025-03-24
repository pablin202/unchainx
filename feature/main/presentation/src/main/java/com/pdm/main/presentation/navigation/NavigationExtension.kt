package com.pdm.main.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

inline fun <reified T> NavGraphBuilder.uxComposable(
    noinline content: @Composable (NavBackStackEntry) -> Unit
) where T : Any, T : Destinations {
    composable<T>(
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(1000)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(1000)
            )
        }
    ) { backStackEntry ->
        content(backStackEntry)
    }
}
