package com.hamzacanbaz.core.items

import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp

@Composable
fun FilterButtonForFilter(clickCounter:Int = 1,btnText:String,selectedFilterName:String = "Rank",btnFontSize:Int = 12,onClick: ()-> Unit){

    val keyboardArrowIconList = arrayListOf(Icons.Rounded.KeyboardArrowUp,Icons.Rounded.KeyboardArrowDown)

    Button(onClick = onClick) {
        Text(text = btnText,color = Color.White, fontSize = btnFontSize.sp)
        if(selectedFilterName == btnText){
            Icon(
                imageVector = keyboardArrowIconList[clickCounter],
                tint = Color.White,
                contentDescription = null
            )
        }
    }
}

@Composable
fun FilterButtonForFav(clickCounter:Int = 0,btnText:String,btnFontSize:Int = 12,onClick: ()-> Unit){

    val favIcon = Icons.Rounded.Favorite

    Button(onClick = onClick) {
        Text(text = btnText,color = Color.White, fontSize = btnFontSize.sp)
        if(clickCounter != 0){
            Icon(
                imageVector = favIcon,
                tint = Color.White,
                contentDescription = null
            )
        }
    }
}