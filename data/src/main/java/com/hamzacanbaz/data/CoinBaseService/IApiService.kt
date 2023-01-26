package com.hamzacanbaz.data.CoinBaseService

import com.hamzacanbaz.data.CoinBaseResponseModel.AllAssets.AllAssetsModel
import retrofit2.Call
import retrofit2.http.GET

interface IApiService {
    @GET("assets/")
    fun getCoinListWithBaseURL(): Call<AllAssetsModel>

}