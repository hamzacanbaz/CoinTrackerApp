package com.hamzacanbaz.cointracker.screen.coinDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamzacanbaz.domain.model.coinDetail.Coin
import com.hamzacanbaz.domain.model.coinPriceHistory.Data
import com.hamzacanbaz.domain.usecase.GetCoinDetailHistoryUseCase
import com.hamzacanbaz.domain.usecase.GetCoinDetailUseCase
import com.hamzacanbaz.domain.util.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    private val getCoinDetailHistoryUseCase: GetCoinDetailHistoryUseCase
) : ViewModel() {

    var coinDetail: Coin by mutableStateOf(Coin())
//    var coinDetailHistory: List<Data> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    private val coinDetailHistory = mutableStateOf<ResultData<List<Data>>>(ResultData.Loading())
    fun getCoinDetailHistory(): State<ResultData<List<Data>>> = coinDetailHistory



    fun getCoinDetail(coinName: String?) {
        viewModelScope.launch {
            if (coinName != null) {
                getCoinDetailUseCase.invoke(coinName.removeSuffix("}").removePrefix("{"))
                    .collect { result ->
                        when (result) {
                            is ResultData.Success -> {
                                coinDetail = result.data?.data ?: Coin()

                                println(coinDetail)
                            }
                            is ResultData.Failed -> {
                                errorMessage = result.errorMessage.toString()
                            }
                            is ResultData.Loading -> {}
                        }

                    }
            }

        }
    }

    fun getCoinDetailHistoryFromRemote(coinName: String?, timeInterval: String) {
        viewModelScope.launch {
            coinDetailHistory.value = ResultData.Loading()
            if (coinName != null) {
                getCoinDetailHistoryUseCase.invoke(
                    coinName.removeSuffix("}").removePrefix("{"),
                    timeInterval
                ).collect { result ->
                    when (result) {
                        is ResultData.Success -> {
                            coinDetailHistory.value = ResultData.Success(result.data?.data ?: listOf())
                        }
                        is ResultData.Failed -> {
                            coinDetailHistory.value = ResultData.Failed(errorMessage=result.errorMessage.toString())
                        }
                        is ResultData.Loading -> {}
                    }

                }
            }

        }
    }


}