package com.hamzacanbaz.data.remote

import com.hamzacanbaz.domain.model.allcoins.AllCoinsResponse
import com.hamzacanbaz.domain.model.coinDetail.CoinDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoService {
    @GET("assets/")
    suspend fun getAllCoinList(): AllCoinsResponse

    @GET("assets/{coinId}")
    suspend fun getCoinDetail(
        @Path("coinId") coinId: String,
    ): CoinDetailResponse

}