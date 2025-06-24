package com.example.archivai.data.source.remote.endpoint.home

import com.example.archivai.data.source.remote.responseModels.home.GetDocsResponseModel
import com.example.archivai.data.source.remote.responseModels.home.GetStorageResponseModel
import com.example.archivai.data.source.remote.responseModels.home.QuickAccessSectionsResponseModel
import com.example.archivai.data.source.remote.responseModels.home.UserDetailsResponseModel
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeApiService {

    @GET("/api/Statistics/docs")
    suspend fun getDocs(
        @Header("Authorization") token : String,
    ): GetDocsResponseModel

    @GET("/api/Statistics/storage")
    suspend fun getStorage(
        @Header("Authorization") token: String,
    ): GetStorageResponseModel

    @GET("/api/Employees/me")
    suspend fun getUserDetails(
        @Header("Authorization") token: String,
    ) : UserDetailsResponseModel

    @GET("/api/Employees/MyRecentVisits")
    suspend fun getQuickAccessSections(
        @Header("Authorization") token: String
    ) : QuickAccessSectionsResponseModel




}