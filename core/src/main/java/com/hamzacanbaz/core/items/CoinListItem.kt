package com.hamzacanbaz.core.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.hamzacanbaz.domain.model.allcoins.Coin
import kotlin.math.roundToInt


@Composable
fun CoinItem(item: Coin){

    val coinValue = decimalPointChanger(item.priceUsd,4.0)
    val coinChangeRate = decimalPointChanger(item.changePercent24Hr,2.0)
    val colorChangeValue = changeColor(coinChangeRate.toFloat())
    val imageVector = arrowIconDirection(coinChangeRate = coinChangeRate.toFloat())

    Card(shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.DarkGray,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 8.dp)) {

        Row(modifier = Modifier.height(100.dp)) {

                Image(painter = rememberImagePainter(data = "https://coinicons-api.vercel.app/api/icon/" + item.symbol.lowercase(),
                    builder = {
                        scale(Scale.FILL)
                        transformations(CircleCropTransformation())
                    }),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                        .align(Alignment.CenterVertically)
                        .weight(1f),
                    contentDescription = null)

            Column( modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
                .weight(2f)
                .align(Alignment.CenterVertically)) {
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

            Column( modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(start = 8.dp, end = 32.dp, top = 16.dp, bottom = 16.dp)
                .weight(2f)
                .align(Alignment.CenterVertically)) {
                Text(text = "$coinValue $",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "%$coinChangeRate",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold,
                        color = colorChangeValue,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth().weight(0.8f)
                    )
                    Icon(
                        imageVector = imageVector,
                        tint = colorChangeValue,
                        contentDescription = "Clear Icon",
                        modifier = Modifier.height(20.dp).width(20.dp).weight(0.2f)
                    )
                    }

                }

            }
        }
    }

private fun arrowIconDirection(coinChangeRate: Float):ImageVector{
    if(coinChangeRate < 0){
        return Icons.Rounded.KeyboardArrowDown
    }else if(coinChangeRate == 0f){
        return Icons.Rounded.KeyboardArrowUp
    }else{
        return Icons.Rounded.KeyboardArrowUp
    }
}

private fun decimalPointChanger(coinValueString:String, pointCount:Double):String{
    val coinValue = (((coinValueString.toFloat() * Math.pow(10.0,pointCount)).roundToInt() /Math.pow(10.0,pointCount)).toFloat()).toString()
    return coinValue
}

private fun changeColor(coinChangeRate:Float):Color{
    var colorChangeValue:Color
    if(coinChangeRate<0){
        colorChangeValue = Color.Red
    }else if(coinChangeRate == 0f){
        colorChangeValue = Color.White
    }else{
        colorChangeValue = Color.Green
    }
    return  colorChangeValue
}

