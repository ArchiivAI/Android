package com.example.archivai.data.di.sections

import com.example.archivai.data.repository.sections.SectionsRepositoryImpl
import com.example.archivai.data.source.remote.endpoint.sections.SectionsApiService
import com.example.archivai.domain.repository.sections.SectionsRepository
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