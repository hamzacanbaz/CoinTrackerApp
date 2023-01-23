package com.hamzacanbaz.core.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector


sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : NavigationItem(
        route = "coinListScreenRoute",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Settings : NavigationItem(
        route = "settingsScreenRoute",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}