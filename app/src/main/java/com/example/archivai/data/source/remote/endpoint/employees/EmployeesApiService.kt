package com.example.archivai.data.source.remote.endpoint.employees

import com.example.archivai.data.source.remote.requestModels.employees.AddEmployeeRequestModel
import com.example.archivai.data.source.remote.responseModels.employees.DeleteEmployeeResponse
import com.example.archivai.data.source.remote.requestModels.employees.RenameEmployeeRequest
import com.example.archivai.data.source.remote.responseModels.employees.AddEmployeeResponseModel
import com.example.archivai.data.source.remote.responseModels.employees.EmployeeResponseModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface EmployeesApiService {


    //get Employees
    @GET("/api/Employees")
    suspend fun getEmployees(
        @Header("Authorization") token : String,
        @Query("page")page : Int =1,
        @Query("pageSize") pageSize : Int =10

    ) : List<EmployeeResponseModel>


    // get roles of an employee
    @GET("/api/Employees/{userId}/roles/{page}")
    suspend fun getRolesOfEmployee(
    )


    //delete employee
    @DELETE("/api/Employees/{userId}")
    suspend fun deleteEmployee(
        @Header("Authorization") token : String,
        @Path("userId") userId : Int

    ) : DeleteEmployeeResponse


    //add Employee
    @POST("/api/Employees")
    suspend fun addEmployee(
        @Header("Authorization") token : String,
        @Body request: AddEmployeeRequestModel
    ) : AddEmployeeResponseModel

    //rename employee
    @PUT("/api/Employees")
    suspend fun renameEmployee(
        @Header("Authorization") token : String,
        @Body renameEmployeeRequest: RenameEmployeeRequest
    )









}