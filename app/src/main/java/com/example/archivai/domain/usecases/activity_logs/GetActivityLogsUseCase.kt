package com.example.archivai.domain.usecases.activity_logs

import com.example.archivai.domain.entities.ActivityLog
import com.example.archivai.domain.repository.ActivityLogRepository
import javax.inject.Inject

class GetActivityLogsUseCase @Inject constructor(private val activityLogRepository: ActivityLogRepository) {

    suspend operator fun invoke(): List<ActivityLog> = activityLogRepository.getActivityLogs()

}