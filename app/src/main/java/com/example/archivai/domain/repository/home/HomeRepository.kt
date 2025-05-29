package com.example.archivai.domain.repository.home

import com.example.archivai.data.source.remote.responseModels.home.GetDocsResponseModel
import com.example.archivai.domain.entities.StorageDoc
import com.example.archivai.domain.entities.StorageUsed

interface HomeRepository {
    suspend fun getStorageUsed() : StorageUsed
    suspend fun getDocs() : Result<GetDocsResponseModel>
    suspend fun getActivityLog()
    suspend fun getQuickAccess()

}