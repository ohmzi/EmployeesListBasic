package com.example.rest_retro

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rest_retro.adapter.EmployeesListAdapter2
import com.example.rest_retro.data.EmployeeData
import com.example.rest_retro.viewModel.ViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), EmployeesListAdapter2.RowClickListener {

    //  private val employeesListAdapter2 by lazy { EmployeesListAdapter2(this) }
    private lateinit var employeesListAdapter2: EmployeesListAdapter2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        employeesListRecyclerview2.apply {
            employeesListAdapter2 = EmployeesListAdapter2(this@MainActivity)

            layoutManager = LinearLayoutManager(this@MainActivity)
            employeesListAdapter2.also { adapter = it }
        }
    }


    private fun initViewModel() {
        val viewModel: ViewModel =
            ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this) {
            if (it != null) {
                employeesListAdapter2.submitList(it.data)
            } else {
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.makeAPICall()
        }

    override fun onDeleteUserClickListener(data: EmployeeData.Data) {
        Toast.makeText(this, "trying to delete ${data.employeeName}", Toast.LENGTH_SHORT).show()
    }


}
