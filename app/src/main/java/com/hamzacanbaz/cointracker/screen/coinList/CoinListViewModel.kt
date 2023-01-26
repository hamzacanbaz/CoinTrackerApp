package com.hamzacanbaz.cointracker.screen.coinList

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamzacanbaz.data.CoinBaseResponseModel.AllAssets.AllAssetsModel
import com.hamzacanbaz.data.CoinBaseResponseModel.AllAssets.Data
import com.hamzacanbaz.data.CoinBaseService.ApiUtil
import com.hamzacanbaz.data.CoinBaseService.IApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinListViewModel:ViewModel() {
    var coinListResponse:List<Data> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    val apiUtil = ApiUtil()

    fun getCoinList(){
        viewModelScope.launch {
            try {
                apiUtil.getCoinList().getCoinListWithBaseURL().enqueue(object : Callback<AllAssetsModel>{
                    override fun onResponse(call: Call<AllAssetsModel>, response: Response<AllAssetsModel>) {
                        coinListResponse = response.body()!!.data
                    }

                    override fun onFailure(call: Call<AllAssetsModel>, t: Throwable) {
                        errorMessage = t.message.toString()
                    }

                }
                )
            }catch (e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }


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