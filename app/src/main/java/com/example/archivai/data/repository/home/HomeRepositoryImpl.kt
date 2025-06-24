package com.example.archivai.data.repository.home

import android.util.Log
import com.example.archivai.data.source.remote.endpoint.home.HomeApiService
import com.example.archivai.data.source.remote.responseModels.home.GetDocsResponseModel
import com.example.archivai.data.utils.SharedPrefsHelper
import com.example.archivai.domain.entities.QuickAccessSection
import com.example.archivai.domain.entities.StorageDoc
import com.example.archivai.domain.entities.StorageUsed
import com.example.archivai.domain.entities.UserDetails
import com.example.archivai.domain.repository.home.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(val homeApiService: HomeApiService) : HomeRepository  {
    val token =  "Bearer ${SharedPrefsHelper.getToken()}"
    override suspend fun getStorageUsed(): StorageUsed {
        val storage = homeApiService.getStorage(token).toStorageResponse()
        Log.d("HomeRepoImpl", "getStorageUsed: $storage")
        return storage
    }

    override suspend fun getDocs(): StorageDoc {

         val docs = homeApiService.getDocs(token).toDocsResponse()
        Log.d("HomeRepositoryImpl", "getDocs: $docs")
        return docs
    }

    override suspend fun getActivityLog() {
        TODO("Not yet implemented")
    }


    override suspend fun getUserDetails() : UserDetails {
        return homeApiService.getUserDetails(token).toUserDetails()
    }

    override suspend fun getQuickAccessSections(): List<QuickAccessSection> {
        return homeApiService.getQuickAccessSections(token).toQuickAccessSections()
    }
}