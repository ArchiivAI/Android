package com.example.archivai.data.di.roles

import com.example.archivai.data.repository.roles.RolesRepositoryImpl
import com.example.archivai.data.source.remote.endpoint.roles.RolesApiService
import com.example.archivai.domain.repository.roles.RoleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RolesModule {


    @Provides
    @Singleton
    fun provideRolesApiService(retrofit: Retrofit) : RolesApiService =
        retrofit.create(RolesApiService::class.java)


    @Provides
    @Singleton
    fun provideRolesRepository(rolesApiService: RolesApiService) : RoleRepository =
        RolesRepositoryImpl(rolesApiService)
}