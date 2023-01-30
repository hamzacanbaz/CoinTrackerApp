package com.hamzacanbaz.cointracker.screen.coinDetail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hamzacanbaz.core.navigation.Destination

object CoinDetailDestination : Destination {
    override val route: String = "coinDetailScreenRoute"
}

fun NavGraphBuilder.coinDetailScreenGraph() {
    composable(
        route = "${CoinDetailDestination.route}/{coinDetailName}",
    ) {
        val coinDetailName = it.arguments?.getString("coinDetailName")
        CoinDetailScreen(coinDetailName)
    }
}