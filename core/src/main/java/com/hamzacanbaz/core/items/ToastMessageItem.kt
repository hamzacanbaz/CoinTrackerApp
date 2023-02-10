package com.hamzacanbaz.core.items

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

fun showToast(context:Context,message:String){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}