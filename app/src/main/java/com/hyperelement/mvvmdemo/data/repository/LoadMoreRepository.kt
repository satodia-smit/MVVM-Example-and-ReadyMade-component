package com.hyperelement.mvvmdemo.data.repository

import android.content.Context
import com.hyperelement.mvvmdemo.data.datasources.models.generic.EmployeeEntity

class LoadMoreRepository(
    private val context: Context
) {

    suspend fun getEmployee(aNumber: Int): List<EmployeeEntity> {
        val mEmployeeList = mutableListOf<EmployeeEntity>()
        for (x in (aNumber * 10) + 1..(aNumber * 10) + 10) {
            mEmployeeList.add(
                EmployeeEntity(
                    x,
                    "Emp $x"
                )
            )
        }
        return mEmployeeList
    }
}