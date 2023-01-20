package com.hamzacanbaz.cointracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.hamzacanbaz.cointracker.ui.coinList.CoinListDestination
import com.hamzacanbaz.cointracker.ui.coinList.CoinListScreen
import com.hamzacanbaz.cointracker.ui.coinList.coinListScreenGraph
import com.hamzacanbaz.cointracker.ui.splash.SplashScreenDestination
import com.hamzacanbaz.cointracker.ui.splash.splashScreenGraph
import com.hamzacanbaz.cointracker.ui.theme.CoinTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinTrackerTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = SplashScreenDestination.route
                ){
                    splashScreenGraph(onSplashScreenComplete = {
                        navController.navigate(route = CoinListDestination.route){
                            popUpTo(SplashScreenDestination.route){
                                inclusive = true
                            }
                        }
                    })
                    coinListScreenGraph()
                }
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