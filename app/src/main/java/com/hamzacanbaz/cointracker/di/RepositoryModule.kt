package com.hamzacanbaz.cointracker.di

import com.hamzacanbaz.data.CryptoRepositoryImpl
import com.hamzacanbaz.data.remote.RemoteDataSource
import com.hamzacanbaz.domain.repo.CryptoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCryptoService(
        remoteDataSource: RemoteDataSource
    ): CryptoRepository = CryptoRepositoryImpl(remoteDataSource)

}
