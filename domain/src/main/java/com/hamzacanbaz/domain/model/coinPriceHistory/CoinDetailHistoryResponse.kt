package com.hamzacanbaz.domain.model.coinPriceHistory

data class CoinDetailHistoryResponse(
    val `data`: List<Data>,
    val timestamp: Long
)