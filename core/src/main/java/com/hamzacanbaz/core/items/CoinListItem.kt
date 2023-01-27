package com.hamzacanbaz.core.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.hamzacanbaz.domain.model.Coin
import kotlin.math.roundToInt


@Composable
fun CoinItem(item: Coin){
    // TODO BURADA Coin olmamali onun yerine string, int seklinde olmali

    var coinValue:Float = item.priceUsd.toFloat()
    coinValue = ((coinValue * 10000.0).roundToInt() / 10000.0).toFloat()
    var coinChangeRate:Float = item.changePercent24Hr.toFloat()
    coinChangeRate = ((coinChangeRate * 100.0).roundToInt() / 100.0).toFloat()
    val coinValueStr:String = coinValue.toString()
    val coinChangeStr:String = coinChangeRate.toString()

    Card(shape = RoundedCornerShape(8.dp),
    backgroundColor = Color.DarkGray,
    modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 8.dp)) {

        Row() {
            Image(painter = rememberImagePainter(data = "https://coinicons-api.vercel.app/api/icon/" + item.symbol.lowercase(),
                builder = {
                    scale(Scale.FILL)
                    transformations(CircleCropTransformation())
                }),
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp).padding(16.dp),
                contentDescription = null)
            
            Column {
                Text(text = item.name,
                    style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(text = item.symbol,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "$coinValueStr $",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.End
                )
                Text(text = "%$coinChangeStr",
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}


