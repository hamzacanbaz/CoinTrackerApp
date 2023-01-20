package com.hamzacanbaz.cointracker.ui.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource


private const val alphaInitial = 0f
private const val alphaTarget = 1f
private const val alphaDuration = 1000

@Composable
internal fun SplashScreen(
    onSplashScreenComplete: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        contentAlignment = Alignment.Center
    ) {
        var targetValue by remember { mutableStateOf(alphaInitial) }

        val alpha: Float by animateFloatAsState(
            targetValue = targetValue,
            animationSpec = tween(alphaDuration),
            finishedListener = {
                onSplashScreenComplete()
            }
        )

        Image(
            painter = painterResource(id = android.R.drawable.ic_delete),
            contentDescription = null,
            modifier = Modifier.alpha(alpha)
        )

        LaunchedEffect(Unit) {
            targetValue = alphaTarget
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


