package com.example.rest_retro.retrofit

import com.example.rest_retro.data.EmployeeData
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {
    @GET("V2")
    fun getEmployeeList():  Call<List<EmployeeData>>

}