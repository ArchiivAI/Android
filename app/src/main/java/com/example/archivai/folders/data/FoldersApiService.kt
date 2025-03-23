package com.example.archivai.folders.data

import com.example.archivai.folders.data.models.createFolder.CreateFolderRequest
import com.example.archivai.folders.data.models.createFolder.CreateFolderResponse
import com.example.archivai.folders.data.models.createSubFolder.CreateSubFolderRequest
import com.example.archivai.folders.data.models.createSubFolder.CreateSubFolderResponse
import com.example.archivai.folders.data.models.deleteFolder.DeleteFolderResponse
import com.example.archivai.folders.data.models.getAllFolders.GetAllFoldersResponse
import com.example.archivai.folders.data.models.getFoldersDetailsInSection.GetFolderDetailsInSectionResponse
import com.example.archivai.folders.data.models.renameFolder.RenameFolderRequest
import com.example.archivai.folders.data.models.renameFolder.RenameFolderResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FoldersApiService {



    // get folders details in section
    @Headers("accept: */*")
    @GET("/api/Sections/folders/{sectionId}/{page}")
    suspend fun getFolderInSection(
        @Header("Authorization") token : String,
    ) : GetFolderDetailsInSectionResponse



    //get all Folders
    @Headers("accept: */*")
    @GET("/api/Folders")
    suspend fun getAllFolders(
        @Header("Authorization") token : String,

    ) : GetAllFoldersResponse


    // delete folder
    @Headers("accept: */*")
    @DELETE("/api/Folders/{folderId}")
    suspend fun deleteFolder(
        @Header("Authorization") token : String,
        @Path ("folderId") folderId : Int



    ): DeleteFolderResponse


    //create folder in Section
    @Headers("accept: */*" , "Content-Type: application/json")
    @POST("/api/Folders")
    suspend fun createFolder(
        @Body request: CreateFolderRequest,
        @Header("Authorization") token : String,
    ) : CreateFolderResponse


    //create Folder in Folder
    @Headers("accept: */*" , "Content-Type: application/json")
    @POST("/api/Folders")
    suspend fun createSubFolder(
        @Body request: CreateSubFolderRequest,
        @Header("Authorization") token : String,
    ) : CreateSubFolderResponse

    //rename Folder
    @Headers("accept: */*" , "Content-Type: application/json")
    @PUT("/api/Folders/{folderId}/rename")
    suspend fun renameFolder(
        @Header("Authorization") token : String,
        @Path ("folderId") folderId : Int,
        @Body renameFolderRequest: RenameFolderRequest
    ) : RenameFolderResponse










}