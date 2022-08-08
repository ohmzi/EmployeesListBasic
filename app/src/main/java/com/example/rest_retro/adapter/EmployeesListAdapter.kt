package com.example.rest_retro.adapter

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rest_retro.R
import com.example.rest_retro.data.EmployeeData
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.employee_list_row.view.*

class EmployeesListAdapter(private val activity: Activity) :
    RecyclerView.Adapter<EmployeesListAdapter.MyViewHolder>() {

    private var employeeList: List<EmployeeData.Data>? = null

    fun setEmployeeList(employeeList: List<EmployeeData.Data>?) {
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
        private val profileImage: ImageView = view.tv_Profile_image
        private val employeeName: TextView = view.tv_Employee_name
        private val employeeId: TextView = view.tv_Id
        private val employeeAge: TextView = view.tv_Employee_age
        private val employeeSalary: TextView = view.tv_Employee_salary


        fun bind(data: EmployeeData.Data, activity: Activity) {
            data.employeeName.also { employeeName.text = it }
            ("ID: " + data.id).also { employeeId.text = it }
            ("Age: " + data.employeeAge).also { employeeAge.text = it }
            ("Salary: " + data.employeeSalary).also { employeeSalary.text = it }
            GlideToVectorYou.justLoadImage(
                activity,
                Uri.parse(data.profileImage),
                profileImage
            )

        }


    }
}