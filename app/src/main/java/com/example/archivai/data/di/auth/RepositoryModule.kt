package com.example.archivai.data.di.auth

import android.content.Context
import com.example.archivai.data.source.remote.endpoint.auth.AuthApiService
import com.example.archivai.data.repository.auth.AuthRepositoryImpl
import com.example.archivai.domain.repository.auth.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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