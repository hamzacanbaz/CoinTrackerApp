package com.hamzacanbaz.data.CoinBaseResponseModel.AllAssets


import com.google.gson.annotations.SerializedName

data class AllAssetsModel(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("timestamp")
    val timestamp: Long
)