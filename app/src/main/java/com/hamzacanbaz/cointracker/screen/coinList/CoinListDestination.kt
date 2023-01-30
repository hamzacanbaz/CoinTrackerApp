package com.hamzacanbaz.cointracker.screen.coinList

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hamzacanbaz.core.navigation.Destination

object CoinListDestination : Destination {
    override val route: String = "coinListScreenRoute"
}

fun NavGraphBuilder.coinListScreenGraph(
    goToAlarms: () -> Unit,
    goToCoinDetailScreen: (coinId: String) -> Unit
) {
    composable(
        route = CoinListDestination.route,
    ) { it ->

        CoinListScreen(
            goToAlarms = {
                goToAlarms.invoke()
            }, goToCoinDetailScreen = { id->
                goToCoinDetailScreen(id)
            }
        )
    }
}