package com.hamzacanbaz.cointracker.screen.coinDetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamzacanbaz.cointracker.screen.coinList.CoinListViewModel

@Composable
fun CoinDetailScreen(
    coinName: String? = null
) {
    val viewModel = hiltViewModel<CoinDetailViewModel>()
    viewModel.getCoinDetail()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Greeting(("hello$coinName") ?: "detail")

    }


}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", color = Color.White)
}


