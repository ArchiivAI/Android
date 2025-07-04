package com.example.archivai.domain.repository.activity_log

import com.example.archivai.domain.entities.ActivityLog

interface ActivityLogRepository {
    suspend fun getActivityLogs(): List<ActivityLog>
}