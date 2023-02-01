package com.hamzacanbaz.cointracker.screen.coinList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamzacanbaz.domain.model.allcoins.Coin
import com.hamzacanbaz.domain.usecase.GetAllCoinsListUseCase
import com.hamzacanbaz.domain.util.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getAllCoinsListUseCase: GetAllCoinsListUseCase
) : ViewModel() {

    var coinList: List<Coin> by mutableStateOf(listOf())
    var filtredCoinList:List<Coin> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getCoinList() {
        viewModelScope.launch {
            getAllCoinsListUseCase.invoke().collect { result ->
                when (result) {
                    is ResultData.Success -> {
                        coinList = result.data?.data ?: listOf()
                    }
                    is ResultData.Failed -> {errorMessage = result.errorMessage.toString()}
                    is ResultData.Loading -> {}
                }

            }

        }
    }

    fun performQuery(
        query: String,
        coinList:List<Coin>
    ) {
        val filteredList = ArrayList<Coin>()
        coinList.forEach { coin ->
            if(!query.isNullOrEmpty()){
                if (coin.symbol.lowercase().contains(query.lowercase())) {
                    filteredList.add(coin)
                }
            }else{
                filteredList.add(coin)
            }
        }
        filtredCoinList = filteredList
    }

}