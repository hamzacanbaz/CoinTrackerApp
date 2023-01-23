package com.hamzacanbaz.cointracker.screen.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hamzacanbaz.core.navigation.Destination

object SettingsDestination: Destination {
    override val route: String = "settingsScreenRoute"
}
fun NavGraphBuilder.settingsScreenGraph(){
    composable(route = SettingsDestination.route){
        SettingsScreen()
    }
}