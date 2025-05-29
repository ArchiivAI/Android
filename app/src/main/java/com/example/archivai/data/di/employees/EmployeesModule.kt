package com.example.archivai.data.di.employees

import com.example.archivai.data.repository.employees.EmployeesRepositoryImpl
import com.example.archivai.data.source.remote.endpoint.employees.EmployeesApiService
import com.example.archivai.domain.repository.employees.EmployeesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object EmployeesModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : EmployeesApiService
    = retrofit.create(EmployeesApiService::class.java)


    @Provides
    @Singleton
    fun provideEmployeesRepository(employeesApiService: EmployeesApiService) : EmployeesRepository =
        EmployeesRepositoryImpl(employeesApiService)



}