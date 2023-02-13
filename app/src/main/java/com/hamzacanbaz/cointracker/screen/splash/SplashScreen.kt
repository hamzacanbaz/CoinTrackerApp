package com.hamzacanbaz.cointracker.screen.splash

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hamzacanbaz.cointracker.screen.coinList.CoinListViewModel


private const val alphaInitial = 0f
private const val alphaTarget = 1f
private const val alphaDuration = 3000

@Composable
internal fun SplashScreen(
    onSplashScreenComplete: () -> Unit
) {

    val viewModel = hiltViewModel<CoinListViewModel>()

    viewModel.getCoinList()
    viewModel.performQuery("",viewModel.coinList,"Rank",0)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
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

        LottieAnimation()

        LaunchedEffect(Unit) {
            targetValue = alphaTarget
        }
    }
}

@Composable
fun LottieAnimation(){
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://assets10.lottiefiles.com/packages/lf20_DV5KsPrQIn.json"))
    com.airbnb.lottie.compose.LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
}

