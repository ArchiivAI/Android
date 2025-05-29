package com.example.archivai.data.repository.home

import com.example.archivai.data.source.remote.endpoint.home.HomeApiService
import com.example.archivai.data.source.remote.responseModels.home.GetDocsResponseModel
import com.example.archivai.domain.entities.StorageDoc
import com.example.archivai.domain.entities.StorageUsed
import com.example.archivai.domain.repository.home.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(val homeApiService: HomeApiService) : HomeRepository  {
    override suspend fun getStorageUsed(): StorageUsed {
        TODO("Not yet implemented")
    }

    override suspend fun getDocs(): Result<GetDocsResponseModel> {
        homeApiService.getDocs()
        return TODO("Provide the return value")
    }

    override suspend fun getActivityLog() {
        TODO("Not yet implemented")
    }

    override suspend fun getQuickAccess() {
        TODO("Not yet implemented")
    }
}