package com.hamzacanbaz.domain.usecase

import com.hamzacanbaz.domain.model.allcoins.AllCoinsResponse
import com.hamzacanbaz.domain.repo.CryptoRepository
import com.hamzacanbaz.domain.util.ResultData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCoinsListUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) {

    suspend operator fun invoke(): Flow<ResultData<AllCoinsResponse>> {
        return cryptoRepository.getAllCoinList()
    }
}