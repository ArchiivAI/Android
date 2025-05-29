package com.example.archivai.data.di.home

import com.example.archivai.data.repository.home.HomeRepositoryImpl
import com.example.archivai.data.source.remote.endpoint.auth.AuthApiService
import com.example.archivai.data.source.remote.endpoint.home.HomeApiService
import com.example.archivai.domain.repository.home.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
     fun provideHomeApiService(retrofit: Retrofit) : HomeApiService =
         retrofit.create(HomeApiService::class.java)

    @Provides
    @Singleton
    fun provideHomeRepository(
        homeApiService: HomeApiService
    ): HomeRepository = HomeRepositoryImpl(homeApiService)


}