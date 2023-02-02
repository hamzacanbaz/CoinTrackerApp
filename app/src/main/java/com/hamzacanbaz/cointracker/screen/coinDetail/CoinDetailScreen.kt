package com.hamzacanbaz.cointracker.screen.coinDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamzacanbaz.domain.model.coinDetail.Coin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CoinDetailScreen(
    coinName: String? = null
) {
    val viewModel = hiltViewModel<CoinDetailViewModel>()
    viewModel.getCoinDetail(coinName)
    val coinDetail = viewModel.coinDetail

    LaunchedEffect(key1 = Unit) {
        this.launch() {
            while (true) {
                viewModel.getCoinDetail(coinName)
                delay(3000)
            }
        }
    }


    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Black
    ) {
        App(coinDetail)
    }


}

@Composable
fun App(coinDetail: Coin) {

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, 16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        CoinPriceAndChangePercentage(coinDetail)

        Spacer(modifier = Modifier.height(32.dp))

        Graphic()

        CoinDetailValueItem(
            infoText = "Supply",
            valueText = coinDetail.supply?.formatFloatingPoint(2) ?: ""
        )

        Divider(color = Color.Gray)

        CoinDetailValueItem(
            infoText = "Max Supply",
            valueText = coinDetail.maxSupply?.formatFloatingPoint(2) ?: ""
        )

        Divider(color = Color.Gray)

        CoinDetailValueItem(
            infoText = "Volume",
            valueText = coinDetail.volumeUsd24Hr?.formatFloatingPoint(2) ?: ""
        )

        Divider(color = Color.Gray)


        CoinDetailValueItem(
            infoText = "Volume Weighted Average Price",
            valueText = coinDetail.vwap24Hr?.formatFloatingPoint(2) ?: ""
        )

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", color = Color.White)
}

@Composable
fun CoinPriceAndChangePercentage(coinDetail: Coin){
    Text(
        text = "Current Price",
        color = Color.White,
        fontSize = 16.sp
    )
    Row(verticalAlignment = Alignment.Bottom) {
        Text(
            text = "$" + (coinDetail.priceUsd?.formatFloatingPoint(4) ?: "00"),
            color = Color.White,
            fontSize = 24.sp
        )
        Box(
            Modifier
                .padding(start = 8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Green)
        ) {
            Text(
                text = "+2.51%",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }

    }

}

@Composable
fun Graphic() {
    Box(
        modifier = Modifier
            .height(200.dp)
            .background(Color.Gray)
            .fillMaxWidth()
    )
}

@Composable
fun CoinDetailValueItem(infoText: String, valueText: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .weight(0.5f)
                .padding(start = 16.dp),
            text = infoText,
            color = Color.Gray
        )
        Text(
            modifier = Modifier
                .weight(0.5f)
                .padding(end = 16.dp),
            text = valueText,
            color = Color.White,
            textAlign = TextAlign.End
        )
    }
}

fun String.formatFloatingPoint(afterPointCount: Int): String {
    val split = this.split(".")
    return if (this.length > 1) {
        split[0] + "." + split[1].substring(0, afterPointCount)
    } else {
        split[0] + "." + "0".repeat(afterPointCount)
    }
}

@Preview(showBackground = true)
@Composable
fun previewUi() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        App(Coin())
    }
}


