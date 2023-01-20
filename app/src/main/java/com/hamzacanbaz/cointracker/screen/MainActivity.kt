package com.hamzacanbaz.cointracker.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.hamzacanbaz.cointracker.screen.coinList.CoinListDestination
import com.hamzacanbaz.cointracker.screen.coinList.coinListScreenGraph
import com.hamzacanbaz.cointracker.screen.main.MainScreen
import com.hamzacanbaz.cointracker.screen.splash.SplashScreenDestination
import com.hamzacanbaz.cointracker.screen.splash.splashScreenGraph
import com.hamzacanbaz.cointracker.screen.theme.CoinTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinTrackerTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoinTrackerTheme {
        Greeting("Android")
    }
}