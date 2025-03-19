package com.example.archivai.auth.di

import com.example.archivai.auth.data.AuthApiService
import com.example.archivai.auth.data.repository.AuthRepositoryImpl
import com.example.archivai.auth.domain.AuthRepository
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