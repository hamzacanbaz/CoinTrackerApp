package com.hamzacanbaz.core.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.hamzacanbaz.data.CoinBaseResponseModel.AllAssets.Data
import kotlin.math.roundToInt

/*
@Composable
fun CoinItem(coin: Data) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface {
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = rememberImagePainter(data = coin.symbol,
                        builder = {
                            scale(Scale.FILL)
                            transformations(CircleCropTransformation())
                            //placeholder(R.drawable)
                        }),
                    contentDescription = coin.id, modifier = Modifier.fillMaxHeight().weight(0.2f)
                )

                Column(
                    verticalArrangement = Arrangement.Center, modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = coin.id,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = coin.priceUsd,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.background(Color.LightGray).padding(4.dp)
                    )
                }

            }
        }
    }
}*/

/*@Composable
fun CoinItem(item: Data){
    Box(modifier = Modifier
        .height(100.dp)
        .fillMaxWidth()
        .padding(8.dp)){
        Image(painter = rememberImagePainter(data = "https://coinicons-api.vercel.app/api/icon/" + item.symbol.lowercase(),
        builder = {
            scale(Scale.FILL)
            transformations(CircleCropTransformation())
        }),
        contentDescription = null)
        Column(modifier = Modifier
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = item.rank,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = item.id,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold)
        }
    }
}*/




/*@Composable
fun CoinItem(item:Data){
    Card(
        //shape = MaterialTheme.shapes.medium,
        shape = RoundedCornerShape(8.dp),
        // modifier = modifier.size(280.dp, 240.dp)
        modifier = Modifier.padding(10.dp,5.dp,10.dp,10.dp),
        //set card elevation of the card
        elevation =  10.dp,
        backgroundColor = Color.Black,
    ) {
        Column(modifier = Modifier.clickable(onClick = {  })) {

            Image(painter = rememberImagePainter(data = "https://coinicons-api.vercel.app/api/icon/" + item.symbol.lowercase(),
                builder = {
                    scale(Scale.FILL)
                    transformations(CircleCropTransformation())
                }),
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentDescription = null)

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Title",
                    style = MaterialTheme.typography.subtitle2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Sub title Example code for android + composes app developers.",
                    //maxLines = 1,
                    //overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.White
                )
            }
        }
    }
}*/

@Composable
fun CoinItem(item:Data){

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


