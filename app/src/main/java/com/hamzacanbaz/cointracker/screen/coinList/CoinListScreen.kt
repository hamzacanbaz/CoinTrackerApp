package com.hamzacanbaz.cointracker.screen.coinList

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.hamzacanbaz.cointracker.screen.Greeting

@Composable
fun CoinListScreen(
    name:String?=null
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Cyan
    ) {

        Greeting(name?:"sad")
    }
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


