package com.hamzacanbaz.cointracker.screen.coinList

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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

     var coinList:List<Coin>  by mutableStateOf(listOf())
    var filtredCoinList:List<Coin> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    var loading: Boolean by mutableStateOf(false)




    fun getCoinList() {
        viewModelScope.launch {
            getAllCoinsListUseCase.invoke().collect { result ->
                when (result) {
                    is ResultData.Success -> {
                        coinList = result.data?.data ?: listOf()
                        loading = false
                    }
                    is ResultData.Failed -> {errorMessage = result.errorMessage.toString()
                    loading = false}
                    is ResultData.Loading -> {loading = true}
                }
            }
        }
    }

    fun performQuery(
        query: String,
        coinList:List<Coin>,
        selectedFilterType:String,
        isSortedByIncrease:Int
    ) {
        var filteredList = ArrayList<Coin>()
        coinList.forEach { coin ->
            if(!query.isNullOrEmpty()){
                if (coin.symbol.lowercase().contains(query.lowercase())) {
                    filteredList.add(coin)
                }
            }else{
                filteredList.add(coin)
            }
        }

        when(selectedFilterType){
            "Rank" -> filteredList.sortBy {it.rank.toFloat()}
            "Price" -> filteredList.sortBy {it.priceUsd.toFloat()}
            "24hChange" -> filteredList.sortBy {it.changePercent24Hr.toFloat()}
        }
        Log.e("Deneme",isSortedByIncrease.toString())
        if(isSortedByIncrease == 1)
            filteredList.reverse()


        filtredCoinList = filteredList
    }

}