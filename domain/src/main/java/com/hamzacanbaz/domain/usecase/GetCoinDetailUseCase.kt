package com.hamzacanbaz.domain.usecase

import com.hamzacanbaz.domain.model.coinDetail.CoinDetailResponse
import com.hamzacanbaz.domain.repo.CryptoRepository
import com.hamzacanbaz.domain.util.ResultData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) {

    suspend operator fun invoke(coinId: String): Flow<ResultData<CoinDetailResponse>> {
        return cryptoRepository.getCoinDetail(coinId)
    }
}