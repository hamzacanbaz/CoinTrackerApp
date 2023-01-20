package com.hamzacanbaz.cointracker.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hamzacanbaz.cointracker.screen.coinList.CoinListDestination
import com.hamzacanbaz.cointracker.screen.coinList.coinListScreenGraph
import com.hamzacanbaz.cointracker.screen.settings.settingsScreenGraph
import com.hamzacanbaz.cointracker.screen.splash.SplashScreenDestination
import com.hamzacanbaz.cointracker.screen.splash.splashScreenGraph

@Composable
fun NavGraph(navController: NavHostController) {
    val testName = "hamza"
    NavHost(
        navController = navController,
        startDestination = SplashScreenDestination.route
    ) {
        splashScreenGraph(onSplashScreenComplete = {
            navController.navigate(route = "${CoinListDestination.route}") {
                popUpTo(SplashScreenDestination.route) {
                    inclusive = true
                }
            }
        })
        coinListScreenGraph()
        settingsScreenGraph()
    }
}