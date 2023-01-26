package com.hamzacanbaz.domain.model


import com.google.gson.annotations.SerializedName

data class AllCoins(
    @SerializedName("data")
    val `data`: List<Coin>,
    @SerializedName("timestamp")
    val timestamp: Long
)