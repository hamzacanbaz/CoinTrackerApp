package com.hamzacanbaz.cointracker.ui.coinList

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.hamzacanbaz.cointracker.ui.Greeting

@Composable
fun CoinListScreen(
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Cyan
    ) {

        Greeting("Android")
    }
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


