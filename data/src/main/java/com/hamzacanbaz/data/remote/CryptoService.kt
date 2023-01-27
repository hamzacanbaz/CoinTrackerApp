package com.hamzacanbaz.data.remote

import com.hamzacanbaz.domain.model.AllCoins
import retrofit2.http.GET

interface CryptoService {
    @GET("assets/")
    suspend fun getAllCoinList(): AllCoins

}