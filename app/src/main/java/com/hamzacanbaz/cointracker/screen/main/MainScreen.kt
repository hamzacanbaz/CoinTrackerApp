package com.hamzacanbaz.cointracker.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hamzacanbaz.cointracker.screen.NavHost
import com.hamzacanbaz.cointracker.screen.coinList.CoinListDestination
import com.hamzacanbaz.cointracker.screen.settings.SettingsDestination
import com.hamzacanbaz.cointracker.screen.splash.SplashScreenDestination
import com.hamzacanbaz.core.items.NavigationItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentRoute = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)

    val showBackButton = when (currentRoute.value?.destination?.route) {
        SettingsDestination.route -> false
        CoinListDestination.route -> false
        SplashScreenDestination.route -> false
        else -> true
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Top App Bar")
                },
                navigationIcon = if (showBackButton) {
                    {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(Icons.Filled.ArrowBack, "backIcon")
                        }
                    }
                } else null,
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 10.dp
            )
        }, bottomBar = {
            BottomBar(navHostController = navController)
        }
    ) {
        NavHost(navController = navController)
    }
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val screens = listOf(
        NavigationItem.Home, NavigationItem.Settings
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navHostController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: NavigationItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(label = {
        Text(text = screen.title)
    },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Nav Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}