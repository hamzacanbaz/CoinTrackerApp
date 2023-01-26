package com.hamzacanbaz.cointracker.di


import com.hamzacanbaz.core.util.Constants
import com.hamzacanbaz.data.remote.CryptoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideCryptoService(): CryptoService {
        return Retrofit.Builder()
            .baseUrl(Constants.COIN_CAP_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoService::class.java)
    }

}
