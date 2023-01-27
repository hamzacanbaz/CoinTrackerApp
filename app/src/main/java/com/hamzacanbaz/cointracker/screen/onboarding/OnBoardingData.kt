package com.hamzacanbaz.cointracker.screen.onboarding

import androidx.compose.ui.graphics.Color
import com.hamzacanbaz.cointracker.screen.theme.ColorBlue

data class OnBoardingData(
    val image: Int, val title: String,
    val desc: String,
    val backgroundColor: Color,
    val mainColor: Color = ColorBlue
)