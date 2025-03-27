package com.pdm.main.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Graphs {
    @Serializable
    data object OnBoarding : Graphs

    @Serializable
    data object Start : Graphs

    @Serializable
    data object Main : Graphs
}

sealed interface Destinations {
    @Serializable
    data object OnBoarding : Destinations

    @Serializable
    data object Start : Destinations

    @Serializable
    data object CreateWallet : Destinations

    @Serializable
    data class VerifySeed(val seed: List<String>) : Destinations

    @Serializable
    data object Home : Destinations
}
