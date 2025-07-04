package com.example.archivai.data.source.remote.endpoint.roles

import com.example.archivai.data.source.remote.responseModels.roles.CreateRoleResponseModel
import com.example.archivai.data.source.remote.responseModels.roles.DeleteEmployeeInRoleResponse
import com.example.archivai.data.source.remote.responseModels.roles.DeleteRoleResponse
import com.example.archivai.data.source.remote.responseModels.roles.GetMissingUsersInRoleResponse
import com.example.archivai.data.source.remote.responseModels.roles.RenameRoleResponse
import com.example.archivai.data.source.remote.responseModels.roles.RoleModelResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RolesApiService {


    //get roles
    @GET("/api/Roles")
    suspend fun getRoles(
        @Header("Authorization") token : String,
    ) : List<RoleModelResponse>


    //delete role
    @DELETE("/api/Roles/{roleId}")
    suspend fun deleteRole(
        @Path("roleId") roleId : Int,
        @Header("Authorization") token : String,
    ) : DeleteRoleResponse

    //create role
    @POST("/api/Roles")
    suspend fun createRole(
        @Header("Authorization") token : String,
        @Query("roleName") name : String,
        @Body  employeeIds : List<Int>
    ) : CreateRoleResponseModel

    //rename role
    @PUT("/api/Roles/{RoleId}/Rename")
    suspend fun renameRole(
        @Path("RoleId") roleId: Int,
        @Query("NewName") newName : String,
        @Header("Authorization") token : String
    ) : RenameRoleResponse


    //get permissions of the role
    @GET("/permissions/{RoleId}/{Page}")
    suspend fun getPermissionsOfRole(
        @Header("Authorization") token : String,
    )


    //get missing users in the role
    @GET("/api/Roles/{RoleId}/missing-users/{Page}")
    suspend fun getMissingUsersInRole(
        @Header("Authorization") token : String,
        @Path("RoleID") roleId: Int,
        @Path("Page") page : Int,
        @Query("PageSize") pageSize : Int =10

    ) : GetMissingUsersInRoleResponse


    //delete employee in Role
    @GET("/api/Employees/{EmployeeId}/delete/{RoleId}")
    suspend fun deleteEmployeeInRole(
        @Header("Authorization") token : String,
        @Path("EmployeeId ") employeeId : Int,
        @Path("RoleId ") roleId : Int
    ) : DeleteEmployeeInRoleResponse







}