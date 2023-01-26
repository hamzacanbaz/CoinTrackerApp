package com.hamzacanbaz.cointracker.screen.coinList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamzacanbaz.domain.model.Coin
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
    var errorMessage: String by mutableStateOf("")

    fun getCoinList() {
        viewModelScope.launch {
            getAllCoinsListUseCase.invoke().collect { result ->
                when (result) {
                    is ResultData.Success -> {
                        coinList = result.data?.data ?: listOf()
                    }
                    is ResultData.Failed -> {}
                    is ResultData.Loading -> {}
                }

            }

        }
    }

//    fun getCoinList(){
//        viewModelScope.launch {
//            try {
//                apiUtil.getCoinList().getAllCoinList().enqueue(object : Callback<AllCoins>{
//                    override fun onResponse(call: Call<AllCoins>, response: Response<AllCoins>) {
//                        coinListResponse = response.body()!!.data
//                    }
//
//                    override fun onFailure(call: Call<AllCoins>, t: Throwable) {
//                        errorMessage = t.message.toString()
//                    }
//
//                }
//                )
//            }catch (e:Exception){
//                errorMessage = e.message.toString()
//            }
//        }
//    }


    /* fun performQuery(
         query: String,
     ) {
         // New empty array list which contains filtered list with query.
         val filteredList = ArrayList<Data>()
         // Loop into each actors data to read actors name.
         actorsListData().forEach { actors ->
             // Compare query with actors name to find a match.
             if (actors.actorName.lowercase().contains(query.lowercase())) {
                 // If match is found, add that name to filtered list.
                 filteredList.add(Actors(actors.actorName))
             }
         }
         // Post updated list to existing LiCeData
         _list.postValue(filteredList)
     }*/

}