package com.example.archivai.sections.di

import com.example.archivai.sections.data.SectionsApiService
import com.example.archivai.sections.data.repository.SectionsRepositoryImpl
import com.example.archivai.sections.domain.SectionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SectionsModule {


    @Provides
    @Singleton
    fun provideSectionsApiService(retrofit: Retrofit) : SectionsApiService =
        retrofit.create(SectionsApiService::class.java)


    @Provides
    @Singleton
    fun provideSectionsRepository(sectionsApiService: SectionsApiService) : SectionsRepository =
        SectionsRepositoryImpl(sectionsApiService)

}