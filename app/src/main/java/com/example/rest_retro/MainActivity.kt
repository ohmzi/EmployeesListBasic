package com.example.rest_retro

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rest_retro.adapter.EmployeesListAdapter
import com.example.rest_retro.databinding.ActivityMainBinding
import com.example.rest_retro.viewModel.ViewModel

private lateinit var binding: ActivityMainBinding
//private var recyclerAdapter: EmployeesListAdapter? = null
//private val binding by lazy { ActivityMainBinding(this) }

class MainActivity : AppCompatActivity() {

    private val recyclerAdapter by lazy { EmployeesListAdapter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        binding.employeesListRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.employeesListRecyclerview.adapter =recyclerAdapter
    }


    private fun initViewModel() {
        val viewModel: ViewModel =
            ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this) {
            if (it != null) {
                recyclerAdapter.setEmployeeList(it)
                recyclerAdapter.notifyDataSetChanged() //change to notifyItemChanged
            } else {
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.makeAPICall()
    }

}