package com.hamzacanbaz.cointracker.screen.coinList

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hamzacanbaz.core.navigation.Destination

object CoinListDestination : Destination {
    override val route: String = "coinListScreenRoute"
}

fun NavGraphBuilder.coinListScreenGraph() {
    composable(
        route = CoinListDestination.route,
    ) {
        CoinListScreen()
    }
}