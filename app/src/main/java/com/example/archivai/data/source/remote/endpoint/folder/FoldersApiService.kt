package com.example.archivai.data.source.remote.endpoint.folder

import com.example.archivai.data.source.remote.requestModels.folders.CreateFolderRequestModel
import com.example.archivai.data.source.remote.responseModels.folders.CreateFolderResponseModel
import com.example.archivai.data.source.remote.requestModels.folders.CreateSubFolderRequestModel
import com.example.archivai.data.source.remote.responseModels.folders.CreateSubFolderResponseModel
import com.example.archivai.data.source.remote.responseModels.folders.DeleteFolderResponseModel
import com.example.archivai.data.source.remote.responseModels.folders.GetAllFoldersResponseModel
import com.example.archivai.data.source.remote.responseModels.folders.GetFolderDetailsInSectionResponseModel
import com.example.archivai.data.source.remote.requestModels.folders.RenameFolderRequestModel
import com.example.archivai.data.source.remote.responseModels.folders.RenameFolderResponseModel
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
    ) : GetFolderDetailsInSectionResponseModel



    //get all Folders
    @Headers("accept: */*")
    @GET("/api/Folders")
    suspend fun getAllFolders(
        @Header("Authorization") token : String,

        ) : GetAllFoldersResponseModel


    // delete folder
    @Headers("accept: */*")
    @DELETE("/api/Folders/{folderId}")
    suspend fun deleteFolder(
        @Header("Authorization") token : String,
        @Path("folderId") folderId : Int



    ): DeleteFolderResponseModel


    //create folder in Section
    @Headers("accept: */*" , "Content-Type: application/json")
    @POST("/api/Folders")
    suspend fun createFolder(
        @Body request: CreateFolderRequestModel,
        @Header("Authorization") token : String,
    ) : CreateFolderResponseModel


    //create Folder in Folder
    @Headers("accept: */*" , "Content-Type: application/json")
    @POST("/api/Folders/SubFolder")
    suspend fun createSubFolder(
        @Body request: CreateSubFolderRequestModel,
        @Header("Authorization") token : String,
    ) : CreateSubFolderResponseModel

    //rename Folder
    @Headers("accept: */*" , "Content-Type: application/json")
    @PUT("/api/Folders/{folderId}/rename")
    suspend fun renameFolder(
        @Header("Authorization") token : String,
        @Path("folderId") folderId : Int,
        @Body renameFolderRequestModel: RenameFolderRequestModel
    ) : RenameFolderResponseModel










}