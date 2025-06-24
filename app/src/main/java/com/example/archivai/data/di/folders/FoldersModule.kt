package com.example.archivai.data.di.folders


import com.example.archivai.data.repository.folders.FoldersRepositoryImpl
import com.example.archivai.data.repository.sections.SectionsRepositoryImpl
import com.example.archivai.data.source.remote.endpoint.folder.FoldersApiService
import com.example.archivai.data.source.remote.endpoint.sections.SectionsApiService
import com.example.archivai.domain.repository.folders.FoldersRepository
import com.example.archivai.domain.repository.sections.SectionsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FoldersModule {


    @Provides
    @Singleton
    fun provideFoldersApiService(retrofit: Retrofit): FoldersApiService =
        retrofit.create(FoldersApiService::class.java)


    @Provides
    @Singleton
    fun provideFoldersRepository(foldersApiService: FoldersApiService): FoldersRepository =
        FoldersRepositoryImpl(foldersApiService)

}