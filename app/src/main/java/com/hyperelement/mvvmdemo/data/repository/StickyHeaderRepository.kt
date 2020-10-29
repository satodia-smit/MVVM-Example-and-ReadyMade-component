package com.hyperelement.mvvmdemo.data.repository

import android.content.Context
import com.hyperelement.mvvmdemo.data.datasources.models.generic.EmployeeEntity

class StickyHeaderRepository(
    private val context: Context
) {

    suspend fun getEmployee(): List<EmployeeEntity> {
        val mEmployeeList = mutableListOf<EmployeeEntity>()
        for (x in 1..10) {
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