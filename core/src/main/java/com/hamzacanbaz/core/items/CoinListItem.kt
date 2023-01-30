package com.hamzacanbaz.core.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

    val coinValue = decimalPointChanger(item.priceUsd,4.0)
    val coinChangeRate = decimalPointChanger(item.changePercent24Hr,2.0)
    val colorChangeValue = changeColor(coinChangeRate.toFloat())

    Card(shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.DarkGray,
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 8.dp)) {

        Row {
            Image(painter = rememberImagePainter(data = "https://coinicons-api.vercel.app/api/icon/" + item.symbol.lowercase(),
                builder = {
                    scale(Scale.FILL)
                    transformations(CircleCropTransformation())
                }),
                modifier = Modifier.height(80.dp)
                    .fillMaxWidth().padding(16.dp).weight(1f).align(Alignment.CenterVertically),
                contentDescription = null)

            Column( modifier = Modifier.wrapContentHeight()
                .fillMaxWidth().padding(16.dp).weight(2f).align(Alignment.CenterVertically)) {
                Text(text = item.name,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(text = item.symbol,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Spacer(modifier = Modifier.width(16.dp))

            Column( modifier = Modifier.wrapContentHeight()
                .fillMaxWidth().padding(16.dp).weight(2f).align(Alignment.CenterVertically)) {
                Text(text = "$coinValue $",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(text = "%$coinChangeRate",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = colorChangeValue,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    }
}

private fun decimalPointChanger(coinValueString:String, pointCount:Double):String{
    val coinValue = (((coinValueString.toFloat() * Math.pow(10.0,pointCount)).roundToInt() /Math.pow(10.0,pointCount)).toFloat()).toString()
    return coinValue
}

private fun changeColor(coinChangeRate:Float):Color{
    var colorChangeValue:Color =  Color.Green
    if(coinChangeRate<0){
        colorChangeValue = Color.Red
    }else if(coinChangeRate == 0f){
        colorChangeValue = Color.White
    }else{
        colorChangeValue = Color.Green
    }
    return  colorChangeValue
}

