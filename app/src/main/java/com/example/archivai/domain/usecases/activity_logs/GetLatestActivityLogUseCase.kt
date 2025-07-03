package com.example.archivai.domain.usecases.activity_logs

import com.example.archivai.domain.entities.ActivityLog
import javax.inject.Inject

class GetLatestActivityLogUseCase @Inject constructor(
    private val getActivityLogsUseCase: GetActivityLogsUseCase
) {
    suspend operator fun invoke(): ActivityLog {
        return getActivityLogsUseCase().first()
    }
}

