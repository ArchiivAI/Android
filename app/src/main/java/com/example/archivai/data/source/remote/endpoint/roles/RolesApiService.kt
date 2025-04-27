package com.example.archivai.data.source.remote.endpoint.roles

import com.example.archivai.data.source.remote.responseModels.roles.DeleteEmployeeInRoleResponse
import com.example.archivai.data.source.remote.responseModels.roles.DeleteRoleResponse
import com.example.archivai.data.source.remote.responseModels.roles.GetMissingUsersInRoleResponse
import com.example.archivai.data.source.remote.responseModels.roles.GetRolesResponse
import com.example.archivai.data.source.remote.responseModels.roles.RenameRoleResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RolesApiService {


    //get roles
    @Headers("accept: */*")
    @GET("/api/Roles")
    suspend fun getRoles(
        @Header("Authorization") token : String,
    ) : GetRolesResponse


    //delete role
    @Headers("accept: */*")
    @DELETE("/api/Roles/{roleId}")
    suspend fun deleteRole(
        @Path("roleId") roleId : Int,
        @Header("Authorization") token : String,
    ) : DeleteRoleResponse


    //rename role
    @Headers("accept: */*" , "Content-Type: application/json")
    @PUT("/api/Roles/{RoleId}/Rename")
    suspend fun renameRole(

        @Path("RoleId") RoleId: Int,
        @Query("NewName") NewName : String,
        @Header("Authorization") token : String
    ) : RenameRoleResponse


    //get permissions of the role
    @Headers("accept: */*")
    @GET("/permissions/{RoleId}/{Page}")
    suspend fun getPermissionsOfRole(
        @Header("Authorization") token : String,
    )
    /// /\


    //get missing users in the role
    @Headers("accept: */*")
    @GET("/api/Roles/{RoleId}/missing-users/{Page}")
    suspend fun getMissingUsersInRole(
        @Header("Authorization") token : String,
        @Path("RoleID") RoleId: Int,
        @Path("Page") Page : Int,
        @Query("PageSize") PageSize : Int =10

    ) : GetMissingUsersInRoleResponse


    //delete employee in Role
    @Headers("accept: */*")
    @GET("/api/Employees/{EmployeeId}/delete/{RoleId}")
    suspend fun deleteEmployeeInRole(
        @Header("Authorization") token : String,
        @Path("EmployeeId ") EmployeeId : Int,
        @Path("RoleId ") RoleId : Int
    ) : DeleteEmployeeInRoleResponse







}