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
    var liveDataList: MutableLiveData<List<EmployeeData>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<EmployeeData>> {
        return liveDataList
    }

    fun makeAPICall() {
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getEmployeeList()

        call.enqueue(object : Callback<List<EmployeeData>> {
            override fun onResponse(
                call: Call<List<EmployeeData>>,
                response: Response<List<EmployeeData>>
            ) {
                d("MakeAPICall", "onResponse")
                // liveDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<EmployeeData>>, t: Throwable) {
                d("MakeAPICall", "onFailure")
                //           liveDataList.postValue(null)
            }
        })
    }
}
