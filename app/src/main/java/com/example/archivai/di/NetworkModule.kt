package com.example.archivai.di

import com.example.archivai.data.auth.AuthApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttp() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {chain ->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("accept","*/*")
                .build()
            chain.proceed(request)



        }.build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit =Retrofit.Builder()
        .baseUrl("https://archivai-backend.azurewebsites.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit) : AuthApiService =
        retrofit.create(AuthApiService::class.java)





}