package com.hamzacanbaz.cointracker.screen.coinDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamzacanbaz.domain.model.coinDetail.Coin
import com.hamzacanbaz.domain.usecase.GetCoinDetailUseCase
import com.hamzacanbaz.domain.util.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase
) : ViewModel() {

    var coinDetail: Coin by mutableStateOf(Coin())
    var errorMessage: String by mutableStateOf("")


    fun getCoinDetail(coinName: String?) {
        viewModelScope.launch {
            if (coinName != null) {
                getCoinDetailUseCase.invoke(coinName.removeSuffix("}").removePrefix("{")).collect { result ->
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



}