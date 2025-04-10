package com.example.archivai.sections.data

import com.example.archivai.sections.data.models.createSection.CreateSectionRequest
import com.example.archivai.sections.data.models.createSection.CreateSectionResponse
import com.example.archivai.sections.data.models.deleteSection.DeleteSectionResponse
import com.example.archivai.sections.data.models.getSectionDetails.GetSectionDetailsResponse
import com.example.archivai.sections.data.models.Section
import com.example.archivai.sections.data.models.renameSection.RenameRequest
import com.example.archivai.sections.data.models.renameSection.RenameSectionResponse
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
    ) : List<Section>


    //rename Section
    @Headers("accept: */*")
    @PUT("/api/Sections/{sectionId}/rename")
    suspend fun renameSection(
        @Header("Authorization") token : String,
        @Path("sectionId") sectionId:Int,
        @Body request: RenameRequest

    ) : RenameSectionResponse


    //get Section details
    @Headers("accept: */*")
    @GET("/api/Sections/{sectionId}")
    suspend fun getSectionDetails(
        @Header("Authorization") token : String,
        @Path("sectionId") sectionId:Int
        ) : GetSectionDetailsResponse



    //create section
    @Headers("accept: */*","Content-Type: application/json")
    @POST("/api/Sections")
    suspend fun createSection(
        @Header("Authorization") token : String,
        @Body request: CreateSectionRequest,
    ) : CreateSectionResponse


    //delete section
    @Headers("accept: */*")
    @DELETE("/api/Sections/{sectionId}")
    suspend fun deleteSections(
        @Header("Authorization") token : String,
        @Path("sectionId") sectionId: Int
    ) : DeleteSectionResponse



    //get section permissions by user

    suspend fun getSectionPermissionsByUser(


    )

    //view section's permitted employee

    suspend fun viewPermittedEmployeesForSection(



    )








}