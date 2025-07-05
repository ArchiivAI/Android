package com.example.archivai.data.di.auth

import com.example.archivai.data.source.remote.endpoint.auth.AuthApiService
import com.example.archivai.data.utils.SharedPrefsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val token = SharedPrefsHelper.getToken()
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("accept", "*/*")

                if (!token.isNullOrBlank()) {
                    request.addHeader("Authorization", "Bearer $token")
                }
                chain.proceed(request.build())
            }.build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://archivai-backend.azurewebsites.net")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
        )
        .build()

    @Provides
    @Singleton
    fun
            provideAuthApiService(retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)
}