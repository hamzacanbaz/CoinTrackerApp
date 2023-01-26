package com.hamzacanbaz.data.CoinBaseService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiUtil {

    var BASE_URL = "https://api.coincap.io/v2/"

    fun getCoinList(): IApiService {
        val retrofit: Retrofit = Retrofit.Builder() // retrofit build ediyoruz
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(IApiService::class.java)
    }

}