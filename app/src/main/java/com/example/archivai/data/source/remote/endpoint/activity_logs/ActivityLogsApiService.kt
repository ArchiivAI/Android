package com.example.archivai.data.source.remote.endpoint.activity_logs

import com.example.archivai.data.source.remote.responseModels.activity_logs.ActivityLogResponseModelItem
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ActivityLogsApiService {

    @GET("/api/AuditLogs/raw-logs/{page}")
    suspend fun getActivityLogs(
        @Header("Authorization") token : String,
        @Path("page") page: Int ,
        @Query("pageSize") pageSize: Int
    ): List<ActivityLogResponseModelItem>
}