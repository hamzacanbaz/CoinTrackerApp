package com.hamzacanbaz.data

import com.hamzacanbaz.data.remote.RemoteDataSource
import com.hamzacanbaz.domain.model.AllCoins
import com.hamzacanbaz.domain.repo.CryptoRepository
import com.hamzacanbaz.domain.util.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): CryptoRepository {

    override suspend fun getAllCoinList(): Flow<ResultData<AllCoins>> =
        flow {
            emit(ResultData.Loading())
            try {
                emit(ResultData.Success(remoteDataSource.getAllCoinList()))
            } catch (e: Exception) {
                emit(ResultData.Failed(errorMessage = e.message))
            }
        }

}