package com.example.rest_retro.data


import com.google.gson.annotations.SerializedName

data class EmployeeData(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String, // Successfully! All records has been fetched.
    @SerializedName("status")
    val status: String // success
) {
    data class Data(
        @SerializedName("employee_age")
        val employeeAge: Int, // 61
        @SerializedName("employee_name")
        val employeeName: String, // Tiger Nixon
        @SerializedName("employee_salary")
        val employeeSalary: Int, // 320800
        @SerializedName("id")
        val id: Int, // 1
        @SerializedName("profile_image")
        val profileImage: String
    )
}