package com.hamzacanbaz.cointracker.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.hamzacanbaz.cointracker.screen.coinList.CoinListDestination
import com.hamzacanbaz.cointracker.screen.settings.SettingsDestination

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = CoinListDestination.route,
        title = "Home",
        icon = Icons.Default.Home
    )
    object Settings : BottomBarScreen(
        route = SettingsDestination.route,
        title = "Settings",
        icon = Icons.Default.Settings
    )
}