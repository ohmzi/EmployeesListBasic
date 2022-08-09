package com.example.rest_retro.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rest_retro.R
import com.example.rest_retro.data.EmployeeData
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.list_adapter_layout.view.*


class EmployeesListAdapter2(private val listener: RowClickListener) :
    ListAdapter<EmployeeData.Data, EmployeesListAdapter2.MyViewHolder>(ListDiffCallback()) {

    class MyViewHolder(private val view: View, private val listener: RowClickListener) :
        RecyclerView.ViewHolder(view) {
        private val employeeName: TextView = view.tv_Employee_name
        private val employeeId: TextView = view.tv_Id
        private val employeeAge: TextView = view.tv_Employee_age
        private val employeeSalary: TextView = view.tv_Employee_salary
        private val employeesListRecyclerview2 = view.employeesListRecyclerview2

        fun bind(data: EmployeeData.Data) {
            data.employeeName.also { employeeName.text = it }
            ("ID: " + data.id).also { employeeId.text = it }
            ("Age: " + data.employeeAge).also { employeeAge.text = it }
            ("Salary: " + data.employeeSalary).also {
                employeeSalary.text = it
            }
            view.setOnClickListener {
                Log.d("DeleteRow", "${data.id}")
                listener.onDeleteUserClickListener(data)
            }

        }
    }

    interface RowClickListener {
        fun onDeleteUserClickListener(data: EmployeeData.Data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.list_adapter_layout, parent, false)
        return MyViewHolder(inflater, listener)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(holder.adapterPosition))

    }
}

class ListDiffCallback : DiffUtil.ItemCallback<EmployeeData.Data>() {
    override fun areItemsTheSame(oldItem: EmployeeData.Data, newItem: EmployeeData.Data): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(
        oldItem: EmployeeData.Data,
        newItem: EmployeeData.Data,
    ): Boolean {
        TODO("Not yet implemented")
    }
}

