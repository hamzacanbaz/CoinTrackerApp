package com.hamzacanbaz.domain.repo

import com.hamzacanbaz.domain.model.allcoins.AllCoinsResponse
import com.hamzacanbaz.domain.model.coinDetail.CoinDetailResponse
import com.hamzacanbaz.domain.util.ResultData
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    suspend fun getAllCoinList(): Flow<ResultData<AllCoinsResponse>>
    suspend fun getCoinDetail(coinId: String): Flow<ResultData<CoinDetailResponse>>
}