package com.example.rest_retro.adapter

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rest_retro.R
import com.example.rest_retro.data.EmployeeData
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.employee_list_row.view.*

class EmployeesListAdapter(val activity: Activity) :
    RecyclerView.Adapter<EmployeesListAdapter.MyViewHolder>() {

    private var employeeList: List<EmployeeData>? = null

    fun setEmployeeList(employeeList: List<EmployeeData>?) {
        this.employeeList = employeeList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.employee_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(employeeList?.get(position)!!, activity)
    }

    override fun getItemCount(): Int {
        return if (employeeList == null) 0
        else employeeList?.size!!
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_Profile_image = view.tv_Profile_image
        val tv_Employee_name = view.tv_Employee_name
        val tv_Id = view.tv_Id
        val tv_Employee_age = view.tv_Employee_age
        val tv_Employee_salary = view.tv_Employee_salary


        fun bind(data: EmployeeData, activity: Activity) {
            data.employee_name.also { tv_Employee_name.text = it }
            ("ID: " + data.id).also { tv_Id.text = it }
            ("Age: " + data.employee_age).also { tv_Employee_age.text = it }
            ("Salary: " + data.employee_salary).also { tv_Employee_salary.text = it }
            GlideToVectorYou.justLoadImage(
                activity,
                Uri.parse(data.profile_image),
                tv_Profile_image
            )

        }
    }
}