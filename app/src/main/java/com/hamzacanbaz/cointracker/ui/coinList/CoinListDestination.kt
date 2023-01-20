package com.hamzacanbaz.cointracker.ui.coinList

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hamzacanbaz.cointracker.ui.splash.SplashScreen
import com.hamzacanbaz.cointracker.ui.splash.SplashScreenDestination
import com.hamzacanbaz.core.navigation.Destination

object CoinListDestination: Destination {
    override val route: String = "coinListScreenRoute"
}
fun NavGraphBuilder.coinListScreenGraph(){
    composable(route = CoinListDestination.route){
        CoinListScreen()
    }
}