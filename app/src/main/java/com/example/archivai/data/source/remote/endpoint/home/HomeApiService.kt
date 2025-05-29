package com.example.archivai.data.source.remote.endpoint.home

import com.example.archivai.data.source.remote.responseModels.home.GetDocsResponseModel
import retrofit2.http.GET

interface HomeApiService {

    @GET("/api/Statistics/docs")
    suspend fun getDocs(): GetDocsResponseModel




}