package com.hamzacanbaz.cointracker.screen.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hamzacanbaz.core.navigation.Destination

object SplashScreenDestination : Destination {
    override val route: String = "splashScreenRoute"
}

fun NavGraphBuilder.splashScreenGraph(onSplashScreenComplete: () -> Unit) {
    composable(route = SplashScreenDestination.route) {
        SplashScreen(onSplashScreenComplete = onSplashScreenComplete)
    }
}