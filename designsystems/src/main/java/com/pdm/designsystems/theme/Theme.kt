package com.pdm.designsystems.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = primary,
    secondary = secondary,
    tertiary = tertiary,

    background = backgroundGlobalLight,
    surface = surface,
    surfaceContainer = surfaceContainerLight,

    onPrimary = onPrimary,
    onSecondary = onSecondary,
    onSurface = onSurface
)

private val DarkColorScheme = darkColorScheme(
    primary = primaryDark,
    secondary = secondaryDark,
    tertiary = tertiaryDark,

    background = backgroundGlobalDark,
    surface = surfaceDark,
    surfaceContainer = surfaceContainerDark,

    onPrimary = onPrimaryDark,
    onSecondary = onSecondaryDark,
    onSurface = onSurfaceDark
)

@Composable
fun UnchainXTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(
        LocalDimensions provides Dimensions(),
        LocalShapes provides Shapes(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}