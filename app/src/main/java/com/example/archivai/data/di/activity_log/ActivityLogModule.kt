package com.example.archivai.data.di.activity_log

import com.example.archivai.data.repository.activityLog.ActivityLogRepoImpl
import com.example.archivai.data.source.remote.endpoint.activity_logs.ActivityLogsApiService
import com.example.archivai.domain.repository.activity_log.ActivityLogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ActivityLogModule {

    @Provides
    @Singleton
    fun provideActivityLogService(retrofit: Retrofit) : ActivityLogsApiService{
        return retrofit.create(ActivityLogsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideActivityLogRepository(activityLogsApiService: ActivityLogsApiService): ActivityLogRepository {
        return ActivityLogRepoImpl(activityLogsApiService)
    }




}