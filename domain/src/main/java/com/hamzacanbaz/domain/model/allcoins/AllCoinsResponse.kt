package com.hamzacanbaz.domain.model.allcoins


import com.google.gson.annotations.SerializedName

data class AllCoinsResponse(
    @SerializedName("data")
    val `data`: List<Coin>,
    @SerializedName("timestamp")
    val timestamp: Long
)