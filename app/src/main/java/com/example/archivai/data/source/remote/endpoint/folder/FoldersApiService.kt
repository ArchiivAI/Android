package com.example.archivai.data.source.remote.endpoint.folder

import com.example.archivai.data.source.remote.requestModels.folders.CreateFolderRequestModel
import com.example.archivai.data.source.remote.responseModels.folders.CreateFolderResponseModel
import com.example.archivai.data.source.remote.requestModels.folders.CreateSubFolderRequestModel
import com.example.archivai.data.source.remote.responseModels.folders.CreateSubFolderResponseModel
import com.example.archivai.data.source.remote.responseModels.folders.DeleteFolderResponseModel
import com.example.archivai.data.source.remote.responseModels.folders.GetAllFoldersResponseModel
import com.example.archivai.data.source.remote.responseModels.folders.GetFoldersInSectionResponseModel
import com.example.archivai.data.source.remote.requestModels.folders.RenameFolderRequestModel
import com.example.archivai.data.source.remote.responseModels.folders.RenameFolderResponseModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface FoldersApiService {



    // get folders  in section
    @GET("/api/Sections/folders/{sectionId}/{page}")
    suspend fun getFoldersInSection(
        @Header("Authorization") token : String,
        @Path("sectionId") sectionId: Int,
        @Path("page") page: Int,
        @Query("pageSize") pageSize : Int =10,
        @Query("searchQuery") searchQuery: String? = null

    ) : GetFoldersInSectionResponseModel



    //get all Folders
    @GET("/api/Folders")
    suspend fun getAllFolders(
        @Header("Authorization") token : String,
    ) : GetAllFoldersResponseModel


    // delete folder

    @DELETE("/api/Folders/{folderId}")
    suspend fun deleteFolder(
        @Header("Authorization") token : String,
        @Path("folderId") folderId : Int

    ): DeleteFolderResponseModel


    //create folder in Section
    @POST("/api/Folders")
    suspend fun createFolder(
        @Header("Authorization") token : String,
        @Body request: CreateFolderRequestModel,
    ) : CreateFolderResponseModel


    //create Folder in Folder
    @POST("/api/Folders/SubFolder")
    suspend fun createSubFolder(
        @Header("Authorization") token : String,
        @Body request: CreateSubFolderRequestModel,
    ) : CreateSubFolderResponseModel

    //rename Folder
    @PUT("/api/Folders/{folderId}/rename")
    suspend fun renameFolder(
        @Header("Authorization") token : String,
        @Path("folderId") folderId : Int,
        @Body renameFolderRequestModel: RenameFolderRequestModel
    ) : RenameFolderResponseModel










}