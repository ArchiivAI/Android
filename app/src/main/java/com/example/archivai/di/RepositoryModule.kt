package com.example.archivai.di

import com.example.archivai.data.auth.AuthApiService
import com.example.archivai.data.auth.repository.AuthRepositoryImpl
import com.example.archivai.domain.auth.AuthRepository
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
    fun provideAuthRepository(authApiService: AuthApiService) : AuthRepository =
        AuthRepositoryImpl(authApiService)





}