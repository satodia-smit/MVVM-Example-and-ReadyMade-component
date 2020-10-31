package com.hyperelement.mvvmdemo.ui.demorecyclerview.smartadapter.header.stickyheader

import androidx.lifecycle.MutableLiveData
import com.hyperelement.mvvmdemo.data.datasources.models.generic.ContinentCountry
import com.hyperelement.mvvmdemo.data.repository.StickyHeaderRepository
import com.hyperelement.mvvmdemo.utilities.RootViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class StickyHeaderVM(
    private val mRepository: StickyHeaderRepository
) : RootViewModel() {
    val mContinentCountryList = MutableLiveData<List<ContinentCountry>>()
    fun loadContinentCountry() {
        launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = mRepository.getContinentCountry()
                    if (response.isEmpty()) {
                        Timber.d("NO CONTINENT ARE AVAILABLE")
                    } else {
                        mContinentCountryList.postValue(response)
                    }
                }
            } catch (exception: Exception) {
                Timber.e("ERROR IN FETCHING DATA $exception")
            }
        }
    }
}