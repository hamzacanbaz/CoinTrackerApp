package com.hamzacanbaz.data.remote

import com.hamzacanbaz.domain.model.allcoins.AllCoinsResponse
import com.hamzacanbaz.domain.model.coinDetail.CoinDetailResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val cryptoService: CryptoService
) {

    suspend fun getAllCoinList(): AllCoinsResponse = cryptoService.getAllCoinList()
    suspend fun getCoinDetail(coinId:String): CoinDetailResponse = cryptoService.getCoinDetail(coinId)


}
