package com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter

import androidx.lifecycle.MutableLiveData
import com.hyperelement.mvvmdemo.data.datasources.models.generic.EmployeeEntity
import com.hyperelement.mvvmdemo.data.repository.GenericRepository
import com.hyperelement.mvvmdemo.utilities.RootViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class GenericVM(
    private val mRepository: GenericRepository
) : RootViewModel() {
    val mEmployeeList = MutableLiveData<List<EmployeeEntity>>()
    fun loadEmployeesFromStorage() {
        launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = mRepository.getEmployee()
                    if (response.isEmpty()) {
                        Timber.d("NO EMPLOYEES ARE AVAILABLE")
                    } else {
                        mEmployeeList.postValue(response)
                    }
                }
            } catch (exception: Exception) {
                Timber.e("ERROR IN FETCHING DATA $exception")
            }
        }
    }
}