package com.hamzacanbaz.data.remote

import com.hamzacanbaz.domain.model.allcoins.AllCoinsResponse
import com.hamzacanbaz.domain.model.coinDetail.CoinDetailResponse
import com.hamzacanbaz.domain.model.coinPriceHistory.CoinDetailHistoryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoService {
    @GET("assets/")
    suspend fun getAllCoinList(): AllCoinsResponse

    @GET("assets/{coinId}")
    suspend fun getCoinDetail(
        @Path("coinId") coinId: String,
    ): CoinDetailResponse

    @GET("assets/{coinId}/history/")
    suspend fun getCoinDetailHistory(
        @Path("coinId") coinId: String,
        @Query("interval") timeInterval: String
    ): CoinDetailHistoryResponse

}