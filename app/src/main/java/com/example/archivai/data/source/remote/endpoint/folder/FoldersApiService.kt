package com.example.archivai.data.source.remote.endpoint.folder

import com.example.archivai.data.source.remote.requestModels.folders.CreateFolderRequestModel
import com.example.archivai.data.source.remote.responseModels.folders.CreateFolderResponseModel
import com.example.archivai.data.source.remote.requestModels.folders.CreateSubFolderRequestModel
import com.example.archivai.data.source.remote.responseModels.folders.CreateSubFolderResponseModel
import com.example.archivai.data.source.remote.responseModels.folders.DeleteFolderResponseModel
import com.example.archivai.data.source.remote.responseModels.folders.GetAllFoldersResponseModel
import com.example.archivai.data.source.remote.responseModels.folders.GetFoldersResponseModel
import com.example.archivai.data.source.remote.requestModels.folders.RenameFolderRequestModel
import com.example.archivai.data.source.remote.responseModels.folders.GetFilesResponseModel
import com.example.archivai.data.source.remote.responseModels.folders.RenameFolderResponseModel
import com.example.archivai.data.source.remote.responseModels.folders.UploadFileResponseModel
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
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

    ) : GetFoldersResponseModel



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

    //get files in a folder
    @GET("/api/Files/folder/{folderId}")
    suspend fun getFiles(
        @Header("Authorization") token : String,
        @Path("folderId") folderId: Int,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize : Int =10,
        @Query("searchQuery") searchQuery: String? = null
    ) : GetFilesResponseModel

    @Multipart
    @POST("/api/files/upload/{folderId}")
    suspend fun uploadFile(
        @Header("Authorization") token : String,
        @Path("folderId") folderId: Int,
        @Part file: MultipartBody.Part
    ): Response<UploadFileResponseModel>

    //get subfolders
    @GET("/api/Folders/{parentFolderId}/{page}")
    suspend fun getSubFolders(
        @Header("Authorization") token : String,
        @Path("parentFolderId") folderId: Int,
        @Path("page") page: Int,
        @Query("pageSize") pageSize : Int =10
    ) : GetFoldersResponseModel

    //rename Folder
    @PUT("/api/Folders/{folderId}/rename")
    suspend fun renameFolder(
        @Header("Authorization") token : String,
        @Path("folderId") folderId : Int,
        @Body renameFolderRequestModel: RenameFolderRequestModel
    ) : RenameFolderResponseModel










}