package com.example.archivai.data.source.remote.endpoint.sections

import com.example.archivai.data.source.remote.requestModels.sections.CreateSectionRequestModel
import com.example.archivai.data.source.remote.requestModels.sections.RenameRequestModel
import com.example.archivai.data.source.remote.responseModels.sections.CreateSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.DeleteSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.GetSectionDetailsResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.RenameSectionResponseModel
import com.example.archivai.data.source.remote.responseModels.sections.SectionResponseModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface SectionsApiService {


    //get sections
    @Headers("accept: */*")
    @GET("/api/Sections/paged/{Page}")
    suspend fun getSections(
        @Header("Authorization") token : String,
        @Path("page") page:Int,
        @Query("pageSize") pageSize : Int =10
    ) : List<SectionResponseModel>


    //rename Section
    @Headers("accept: */*")
    @PUT("/api/Sections/{sectionId}/rename")
    suspend fun renameSection(
        @Header("Authorization") token : String,
        @Path("sectionId") sectionId:Int,
        @Body request: RenameRequestModel

    ) : RenameSectionResponseModel


    //get Section details
    @Headers("accept: */*")
    @GET("/api/Sections/{sectionId}")
    suspend fun getSectionDetails(
        @Header("Authorization") token : String,
        @Path("sectionId") sectionId:Int
        ) : GetSectionDetailsResponseModel



    //create section
    @Headers("accept: */*","Content-Type: application/json")
    @POST("/api/Sections")
    suspend fun createSection(
        @Header("Authorization") token : String,
        @Body request: CreateSectionRequestModel,
    ) : CreateSectionResponseModel


    //delete section
    @Headers("accept: */*")
    @DELETE("/api/Sections/{sectionId}")
    suspend fun deleteSections(
        @Header("Authorization") token : String,
        @Path("sectionId") sectionId: Int
    ) : DeleteSectionResponseModel











}