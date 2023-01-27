package com.hamzacanbaz.data.remote

import com.hamzacanbaz.domain.model.AllCoins
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val cryptoService: CryptoService
) {

    suspend fun getAllCoinList(): AllCoins = cryptoService.getAllCoinList()


}
