package com.hamzacanbaz.cointracker.screen.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SettingsScreen(
    name:String?=null
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Cyan
    ) {

        Greeting(name?:"hc")
    }
}
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


