package com.example.archivai.data.repository.activityLog

import android.util.Log
import com.example.archivai.data.source.remote.endpoint.activity_logs.ActivityLogsApiService
import com.example.archivai.data.source.remote.responseModels.activity_logs.toDomain
import com.example.archivai.data.utils.SharedPrefsHelper
import com.example.archivai.domain.entities.ActivityLog
import com.example.archivai.domain.repository.activity_log.ActivityLogRepository

class ActivityLogRepoImpl(
    private val activityLogApiService: ActivityLogsApiService, ) : ActivityLogRepository {
    val token =  "Bearer ${SharedPrefsHelper.getToken()}"
    override suspend fun getActivityLogs(): List<ActivityLog> {
        val logs =  activityLogApiService.getActivityLogs(
            token,
            page = 1,
            pageSize = 20
        ).map { it.toDomain() }
        Log.d("ActivityLogRepoImpl", "Fetched ${logs.size} activity logs")
        return logs
    }
}