package com.example.archivai.data.source.remote.endpoint.employees

import com.example.archivai.data.source.remote.requestModels.employees.AddEmployeeRequest
import com.example.archivai.data.source.remote.responseModels.employees.DeleteEmployeeResponse
import com.example.archivai.data.source.remote.responseModels.employees.GetEmployeesResponse
import com.example.archivai.data.source.remote.requestModels.employees.RenameEmployeeRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface EmployeesApiService {


    //get Employees
    @Headers("accept: */*")
    @GET("/api/Employees")
    suspend fun getEmployees(
        @Header("Authorization") token : String,
        @Query("page")page : Int =1,
        @Query("pageSize") pageSize : Int =10

    ) : GetEmployeesResponse


    // get roles of an employee
    @Headers("accept: */*")
    @GET("/api/Employees/{userId}/roles/{page}")
    suspend fun getRolesOfEmployee(


    )


    //delete employee
    @Headers("accept: */*")
    @DELETE("/api/Employees/{userId}")
    suspend fun deleteEmployee(
        @Header("Authorization") token : String,
        @Path("userId") userId : Int

    ) : DeleteEmployeeResponse


    //add Employee
    @Headers("accept: */*" , "Content-Type: application/json")
    @POST("/api/Employees")
    suspend fun addEmployee(
        @Header("Authorization") token : String,
        @Body request: AddEmployeeRequest
    )

    //rename employee

    @Headers("accept: */*" , "Content-Type: application/json")
    @PUT("/api/Employees")
    suspend fun renameEmployee(
        @Header("Authorization") token : String,
        @Body renameEmployeeRequest: RenameEmployeeRequest


    )









}