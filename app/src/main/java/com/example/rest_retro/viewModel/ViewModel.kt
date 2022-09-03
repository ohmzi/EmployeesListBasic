package com.example.rest_retro.viewModel

import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rest_retro.data.EmployeeData
import com.example.rest_retro.retrofit.RetroInstance
import com.example.rest_retro.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel : ViewModel() {
    var liveDataList: MutableLiveData<MutableList<EmployeeData.Data>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<MutableList<EmployeeData.Data>> {
        return liveDataList
    }

    fun deleteLiveData(data: EmployeeData.Data) {

        val list = liveDataList.value?.toMutableList()
        val toDeleteEmployee = list?.find { it.id == data.id }
        list?.remove(toDeleteEmployee)
        liveDataList.value = list
    }

    fun makeAPICall() {
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getEmployeeList()

        call.enqueue(object : Callback<EmployeeData> {
            override fun onResponse(
                call: Call<EmployeeData>,
                response: Response<EmployeeData>,
            ) {
                d("MakeAPICall", "onResponse ${response.body()}")
                d("MakeAPICall", "onResponse ${response.isSuccessful}")
                response.body()?.data?.let { liveDataList.postValue(it.toMutableList()) }
            }

            override fun onFailure(call: Call<EmployeeData>, t: Throwable) {
                d("MakeAPICall", "onFailure")
                liveDataList.postValue(null)
            }
        })
    }
}
