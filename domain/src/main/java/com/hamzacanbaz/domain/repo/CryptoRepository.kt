package com.hamzacanbaz.domain.repo

import com.hamzacanbaz.domain.model.AllCoins
import com.hamzacanbaz.domain.util.ResultData
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    suspend fun getAllCoinList(): Flow<ResultData<AllCoins>>
}